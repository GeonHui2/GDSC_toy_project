package gdsc.toy_project.domain.photo.service;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.photo.dto.*;
import gdsc.toy_project.domain.photo.entity.Photo;
import gdsc.toy_project.domain.photo.repository.PhotoRepository;
import gdsc.toy_project.domain.user.entity.User;
import gdsc.toy_project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;


    // DB 사진 저장
    @Transactional
    public Long addPhoto(String filePath, UploadPhotoResponse uploadPhotoResponse) {
        User user = userRepository.findByUid(uploadPhotoResponse.getUid()).get();

        Photo photo = Photo.addPhoto(
                filePath,
                uploadPhotoResponse.getCategory(),
                user
        );

        Photo save = photoRepository.save(photo);

        return save.getId();
    }

    // 사진 상세 정보 조회
    public GetPhotoInfo getPhotoInfo(Long photoId) {
        Photo photo = photoRepository.findById(photoId).get();

        GetPhotoInfo photoInfo = new GetPhotoInfo();
        photoInfo.setPhotoPath(photo.getFilePath());
        photoInfo.setCategory(photo.getCategory());
        photoInfo.setUploadedAt(photo.getCreatedAT());

        return photoInfo;
    }

    // 사진 카테고리 변경
    @Transactional
    public void changeCategory(EditCategory editCategory) {
        Photo findPhoto = photoRepository.findById(editCategory.getPhotoId()).get();

        findPhoto.changeCategory(editCategory.getCategory());
    }

    // 사진 삭제
    @Transactional
    public void deletePhoto(Long photoId) {
        Photo photo = photoRepository.findById(photoId).get();

        photoRepository.delete(photo);
    }

    // 카테고리별 리스트 조회
    //public

    // 사진 리스트 조회
    public List<ListPhotoInfo> photoInfoList(Category category, String uid) {
        User user = userRepository.findByUid(uid).get();
        List<ListPhotoInfo> photoInfoList = new ArrayList<>();

        List<Photo> photoList = photoRepository.findAllByCategoryAndUser(category, user, );
        for (Photo p : photoList) {
            ListPhotoInfo listPhotoInfo = new ListPhotoInfo();
            listPhotoInfo.setPhotoId(p.getId());
            listPhotoInfo.setPhotoPath(p.getFilePath());
            listPhotoInfo.setUploadAt(p.getCreatedAT());

            photoInfoList.add(listPhotoInfo);
        }

        return photoInfoList;
    }
}

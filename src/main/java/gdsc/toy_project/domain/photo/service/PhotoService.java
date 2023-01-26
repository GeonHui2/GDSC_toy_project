package gdsc.toy_project.domain.photo.service;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.photo.dto.*;
import gdsc.toy_project.domain.photo.entity.Photo;
import gdsc.toy_project.domain.photo.repository.PhotoRepository;
import gdsc.toy_project.domain.user.entity.User;
import gdsc.toy_project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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

    // 카테고리 유무 조회
    public boolean checkCategoryExist(Category category, String uid) {
        User user = userRepository.findByUid(uid).get();

        Boolean result = photoRepository.existsByCategoryAndUser(category, user);

        return result;
    }

    //카테고리 별 리스트 조회
    public List<ListCategoryInfo> categoryInfo(UidDto uidDto) {
        User user = userRepository.findByUid(uidDto.getUid()).get();
        List<ListCategoryInfo> categoryInfos = new ArrayList<>();

        for (Category c : Category.values()) {
            boolean result = checkCategoryExist(c, uidDto.getUid());
            if (result == false) {
                ListCategoryInfo listCategoryInfo = new ListCategoryInfo();
                listCategoryInfo.setCategory(c);
                listCategoryInfo.setCount(0L);
                listCategoryInfo.setFilePathList(null);

                categoryInfos.add(listCategoryInfo);
            } else if (result == true) {
                PageRequest pageRequest = PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createdAT"));
                log.info("pageRequset size = {}", pageRequest.getPageSize());

                log.info("category = {}", c);
                Page<Photo> byCategoryAndUser = photoRepository.findByCategoryAndUser(c, user, pageRequest);
                log.info("page size = {}", byCategoryAndUser.getTotalElements());
                ListCategoryInfo listCategoryInfo = new ListCategoryInfo();
                listCategoryInfo.setCategory(c);
                listCategoryInfo.setCount(byCategoryAndUser.getTotalElements());

                List<String> filePath = new ArrayList<>();

                for (Photo p : byCategoryAndUser.getContent()) {
                    filePath.add(p.getFilePath());
                }

                listCategoryInfo.setFilePathList(filePath);
                categoryInfos.add(listCategoryInfo);
            }
        }
        return categoryInfos;
    }

    // 사진 리스트 조회
    public Slice<ListPhotoInfo> photoInfoList (GetCategoryInfo getCategoryInfo) {
        User user = userRepository.findByUid(getCategoryInfo.getUid()).get();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createdAT"));
        Slice<Photo> photoList = photoRepository.findAllByCategoryAndUser(getCategoryInfo.getCategory(), user, pageRequest);
        Slice<ListPhotoInfo> photoInfoList = photoList.map(ListPhotoInfo::new);

        return photoInfoList;
    }
}

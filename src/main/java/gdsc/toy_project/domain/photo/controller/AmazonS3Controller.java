package gdsc.toy_project.domain.photo.controller;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.photo.dto.*;
import gdsc.toy_project.domain.photo.service.AwsS3Service;
import gdsc.toy_project.domain.photo.service.PhotoService;
import gdsc.toy_project.global.response.DefaultRes;
import gdsc.toy_project.global.response.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;
    private final PhotoService photoService;


    //사진 업로드
    @PostMapping("/file")
    public ResponseEntity uploadFile(@RequestPart(value = "multipartFile") MultipartFile multipartFile,
                                     @RequestPart(value = "uploadPhotoResponse") UploadPhotoResponse uploadPhotoResponse) {
        String filePath = awsS3Service.uploadFile(multipartFile);
        Long photoId = photoService.addPhoto(filePath, uploadPhotoResponse);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "이미지 업로드 완료", photoId), HttpStatus.OK);
    }

    //사진 삭제
    @DeleteMapping("/file")
    public ResponseEntity deleteFile(@RequestBody DeletePhoto deletePhoto) {
        awsS3Service.deleteFile(deletePhoto.getFilePath());
        photoService.deletePhoto(deletePhoto.getPhotoId());
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "이미지 삭제 완료"), HttpStatus.OK);
    }

    //사진 카테고리 수정
    @PatchMapping("/file")
    public ResponseEntity editCategory(@RequestBody EditCategory editCategory) {
        photoService.changeCategory(editCategory);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "카테고리 변경 완료"), HttpStatus.OK);
    }

    //사진 상세 정보 조회
    @GetMapping("/file/{photoId}")
    public ResponseEntity getPhotoInfo(@PathVariable(name = "photoId") Long photoId) {
        GetPhotoInfo photoInfo = photoService.getPhotoInfo(photoId);

        return photoInfo != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "해당 게시글 조회 완료", photoInfo), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "조회된 게시글 없음"), HttpStatus.OK);
    }

    // 카테고리 별 사진 리스트 조회
    @GetMapping("/file/list")
    public ResponseEntity getCategoryInfo(@RequestBody GetCategoryInfo getCategoryInfo) {
        Slice<ListPhotoInfo> listPhotoInfos = photoService.photoInfoList(getCategoryInfo);

        return listPhotoInfos != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "해당 카테고리 리스트 조회 완료", listPhotoInfos), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "조회된 리스트 없음"), HttpStatus.OK);
    }

    //카테고리 리스트 조회
    @GetMapping("/file/all")
    public ResponseEntity getAllInfo(@RequestBody UidDto uid) {
        List<ListCategoryInfo> categoryInfos = photoService.categoryInfo(uid);

        return categoryInfos != null ?
                new ResponseEntity(DefaultRes.res(StatusCode.OK, "해당 사용자 사진 리스트 조회 완료", categoryInfos), HttpStatus.OK) :
                new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, "조회된 리스트 없음"), HttpStatus.OK);
    }
}

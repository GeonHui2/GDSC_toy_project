package gdsc.toy_project.domain.photo.controller;

import gdsc.toy_project.domain.photo.dto.DeletePhoto;
import gdsc.toy_project.domain.photo.dto.EditCategory;
import gdsc.toy_project.domain.photo.dto.UploadPhotoResponse;
import gdsc.toy_project.domain.photo.dto.UploadS3Response;
import gdsc.toy_project.domain.photo.service.AwsS3Service;
import gdsc.toy_project.domain.photo.service.PhotoService;
import gdsc.toy_project.global.response.DefaultRes;
import gdsc.toy_project.global.response.StatusCode;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/file")
    public ResponseEntity uploadFile(@RequestPart(value = "multipartFile") MultipartFile multipartFile,
                                     @RequestPart(value = "uploadPhotoResponse") UploadPhotoResponse uploadPhotoResponse) {
        String filePath = awsS3Service.uploadFile(multipartFile);
        Long photoId = photoService.addPhoto(filePath, uploadPhotoResponse);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "이미지 업로드 완료", photoId), HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity deleteFile(@RequestBody DeletePhoto deletePhoto) {
        awsS3Service.deleteFile(deletePhoto.getFilePath());
        photoService.deletePhoto(deletePhoto.getPhotoId());
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "이미지 삭제 완료"), HttpStatus.OK);
    }

    @PatchMapping("/file")
    public ResponseEntity editCategory(@RequestBody EditCategory editCategory) {
        photoService.changeCategory(editCategory);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, "카테고리 변경 완료"), HttpStatus.OK);
    }
}

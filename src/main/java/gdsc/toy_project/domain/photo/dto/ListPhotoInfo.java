package gdsc.toy_project.domain.photo.dto;

import gdsc.toy_project.domain.photo.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPhotoInfo {

    private Long photoId;
    private String photoPath;
    private LocalDateTime uploadAt;

    public ListPhotoInfo(Photo p) {
        this.photoId = p.getId();
        this.photoPath = p.getFilePath();
        this.uploadAt = p.getCreatedAT();
    }
}

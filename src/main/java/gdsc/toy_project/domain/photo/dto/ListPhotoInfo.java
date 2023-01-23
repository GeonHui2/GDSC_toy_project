package gdsc.toy_project.domain.photo.dto;

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
}

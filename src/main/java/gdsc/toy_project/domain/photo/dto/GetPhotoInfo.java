package gdsc.toy_project.domain.photo.dto;

import gdsc.toy_project.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotoInfo {

    private String photoPath;
    private LocalDateTime uploadedAt;
    private Category category;
}

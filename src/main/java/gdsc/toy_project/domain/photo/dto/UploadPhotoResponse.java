package gdsc.toy_project.domain.photo.dto;

import gdsc.toy_project.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadPhotoResponse {

    private String uid;
    private Category category;
}

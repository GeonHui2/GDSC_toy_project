package gdsc.toy_project.domain.photo.dto;

import gdsc.toy_project.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadS3Response {

    private final String imageUrl;
    private final Category category;
}

package gdsc.toy_project.domain.photo.dto;

import gdsc.toy_project.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditCategory {

    private Long photoId;
    private Category category;
}

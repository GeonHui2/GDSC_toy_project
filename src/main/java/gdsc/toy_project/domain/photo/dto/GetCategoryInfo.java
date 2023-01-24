package gdsc.toy_project.domain.photo.dto;

import gdsc.toy_project.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryInfo {

    private String uid;
    private Category category;
}

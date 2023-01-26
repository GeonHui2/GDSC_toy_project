package gdsc.toy_project.domain.photo.dto;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.photo.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoryInfo {

    private Category category;
    private Long count;
    private List<String> filePathList;
}

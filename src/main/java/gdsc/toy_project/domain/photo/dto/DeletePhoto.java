package gdsc.toy_project.domain.photo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeletePhoto {

    private Long photoId;
    private String filePath;
}

package gdsc.toy_project.domain.album.dto.response;

import gdsc.toy_project.domain.photo.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AlbumListResponse {

    private String name;
    private Long count;
    private List<Photo> photoList;

}

package gdsc.toy_project.domain.album.repository;

import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.album.entity.Album;
import gdsc.toy_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findAllByUserAndCategory(User user, Category category);
}

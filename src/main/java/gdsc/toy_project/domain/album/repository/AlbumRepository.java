package gdsc.toy_project.domain.album.repository;

import gdsc.toy_project.domain.album.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}

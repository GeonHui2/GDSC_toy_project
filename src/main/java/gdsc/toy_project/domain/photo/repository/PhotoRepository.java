package gdsc.toy_project.domain.photo.repository;


import gdsc.toy_project.domain.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}

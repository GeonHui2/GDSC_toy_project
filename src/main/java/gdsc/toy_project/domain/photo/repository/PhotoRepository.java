package gdsc.toy_project.domain.photo.repository;


import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.photo.entity.Photo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByCategory(Category category, Pageable pageable);
}

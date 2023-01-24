package gdsc.toy_project.domain.photo.repository;


import gdsc.toy_project.domain.Category;
import gdsc.toy_project.domain.photo.entity.Photo;
import gdsc.toy_project.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByCategory(Category category, Pageable pageable);

    Slice<Photo> findAllByCategoryAndUser(Category category, User user, Pageable pageable);

    Boolean existsByCategoryAndUser(Category category, User user);

    Page<Photo> findTop4ByCategoryAndUser(Category category, User user, Pageable pageable);
}

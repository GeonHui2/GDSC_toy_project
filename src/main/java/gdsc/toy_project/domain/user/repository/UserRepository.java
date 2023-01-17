package gdsc.toy_project.domain.user.repository;

import gdsc.toy_project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUid(String uid);

    Optional<User> findByUid(String uid);
}

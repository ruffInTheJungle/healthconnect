package healthconnect.repositories;

import healthconnect.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findOneById (Long id);

    User findOneByUsername (String username);

}

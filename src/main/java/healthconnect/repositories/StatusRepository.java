package healthconnect.repositories;

import healthconnect.models.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findStatusByName (String name);

}

package healthconnect.repositories;

import healthconnect.models.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByPrescribeToUsername(String username);
}

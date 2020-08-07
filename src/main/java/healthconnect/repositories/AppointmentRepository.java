package healthconnect.repositories;

import healthconnect.models.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByPatientUsername (String patientUsername);

    List<Appointment> findAllByDoctorUsernameAndStatusName(String username, String statusName);

    Appointment findOneById(Long id);
}

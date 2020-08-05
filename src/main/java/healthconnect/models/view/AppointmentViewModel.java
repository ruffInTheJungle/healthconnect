package healthconnect.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AppointmentViewModel {

    private Long id;
    private AppointmentUserViewModel doctor;
    private AppointmentUserViewModel patient;
    private String appointmentTime;
    private StatusViewModel status;

}

package healthconnect.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment extends BaseEntity{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User doctor;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private User patient;
    private LocalDateTime appointmentTime;
    @NotNull
    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;


}

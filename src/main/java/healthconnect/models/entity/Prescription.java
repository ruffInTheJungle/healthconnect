package healthconnect.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;

import static healthconnect.messages.ValidationErrorMessages.*;


@Entity
@Table (name = "prescriptions")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prescription extends BaseEntity{

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perscription_doctor_id", referencedColumnName = "id")
    private User prescribedBy;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perscription_patient_id", referencedColumnName = "id")
    private User prescribeTo;
    @NotNull
    @FutureOrPresent(message = INCORRECT_PRESCRIPTION_DATE)
    private LocalDate date;
    @NotNull
    @Size(min = 3, max = 2000, message = INVALID_PRESCRIPTION_MESSAGE)
    private String prescriptionNotes;
    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

}

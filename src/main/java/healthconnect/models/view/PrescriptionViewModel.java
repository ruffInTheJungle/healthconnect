package healthconnect.models.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptionViewModel {

    private Long id;
    private AppointmentUserViewModel prescribedBy;
    private AppointmentUserViewModel prescribeTo;
    private String date;
    private String prescriptionNotes;

}

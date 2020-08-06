package healthconnect.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptionBindingModel {

    private Long appointmentId;
    private Long patientId;
    private String notes;
    private String doctorUsername;

}

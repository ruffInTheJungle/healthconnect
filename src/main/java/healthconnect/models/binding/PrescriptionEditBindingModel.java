package healthconnect.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptionEditBindingModel {

    private long prescriptionId;
    private String notes;

}

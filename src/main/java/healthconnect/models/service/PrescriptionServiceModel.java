package healthconnect.models.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PrescriptionServiceModel extends BaseServiceModel{

    private UserServiceModel prescribedBy;
    private UserServiceModel prescribeTo;
    private String date;
    private String prescriptionNotes;

}

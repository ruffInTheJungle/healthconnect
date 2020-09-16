package healthconnect.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static healthconnect.messages.ValidationErrorMessages.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentConfirmationBindingModel {

    private String id;
    @NotNull(message = APPOINTMENT_DATE_MANDATORY)
    private String dateAndTime;

}

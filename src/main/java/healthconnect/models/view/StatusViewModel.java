package healthconnect.models.view;

import healthconnect.utils.roleValidator.ValidateRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StatusViewModel {

    private Long id;
    private String name;
    private LocalDateTime lastStatusChangeDate;
    private String statusDescription;

}

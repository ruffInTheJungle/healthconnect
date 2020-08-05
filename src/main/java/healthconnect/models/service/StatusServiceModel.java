package healthconnect.models.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StatusServiceModel extends BaseServiceModel {

    private String name;
    private LocalDateTime lastStatusChangeDate;
    private String statusDescription;

}

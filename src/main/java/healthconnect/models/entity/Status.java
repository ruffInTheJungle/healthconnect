package healthconnect.models.entity;

import healthconnect.utils.roleValidator.ValidateRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static healthconnect.messages.ValidationErrorMessages.*;

@Entity
@Table(name = "appointment_statuses")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status extends BaseEntity{


    @NotNull
    @ValidateRole(acceptedValues = {"REQUESTED", "CONFIRMED", "ARCHIVED"}, message = INVALID_STATUS)
    private String name;
    @NotNull
    private LocalDateTime lastStatusChangeDate;
    @NotNull
    @Column(name = "status_description", columnDefinition = "TEXT")
    private String statusDescription;

}

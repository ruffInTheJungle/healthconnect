package healthconnect.models.entity;

import healthconnect.utils.roleValidator.ValidateRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static healthconnect.messages.ValidationErrorMessages.*;

@Entity
@Table(name = "roles")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity{

    @NotNull
    @ValidateRole(acceptedValues={"ROLE_PATIENT", "ROLE_DOCTOR", "ROLE_ADMIN"}, message = INVALID_ROLE)
    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

}

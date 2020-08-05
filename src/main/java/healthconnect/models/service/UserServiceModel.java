package healthconnect.models.service;

import healthconnect.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserServiceModel extends BaseServiceModel{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String salutation;
    private LocalDate birthday;
    private List<RoleServiceModel> roles;
    private LocalDateTime registrationDate;
    private Double rating;
    private boolean enabled;

}

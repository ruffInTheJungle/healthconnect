package healthconnect.models.binding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleBindingModel {

    private String username;
    private boolean patient;
    private boolean doctor;
    private boolean admin;
}

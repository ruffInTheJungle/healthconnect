package healthconnect.models.service;

import healthconnect.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleServiceModel extends BaseServiceModel{

    private String name;
    private User user;

}

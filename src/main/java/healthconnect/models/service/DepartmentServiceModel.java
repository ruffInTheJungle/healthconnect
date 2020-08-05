package healthconnect.models.service;

import healthconnect.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DepartmentServiceModel extends BaseServiceModel {

    private String name;
    private String description;
    private String imageURL;
    private List<UserServiceModel> doctors;

}

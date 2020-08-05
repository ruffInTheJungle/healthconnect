package healthconnect.models.view;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DepartmentViewModel {

    private String name;
    private String description;
    private String imageURL;
    private List<DepartmentDoctorViewModel> doctors;

}

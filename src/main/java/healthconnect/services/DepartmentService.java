package healthconnect.services;

import healthconnect.models.service.DepartmentServiceModel;
import healthconnect.models.view.DepartmentViewModel;

import java.util.List;

public interface DepartmentService {
    List<DepartmentViewModel> getAllDepartments();

    DepartmentServiceModel getDepartmentByName(String name);
}

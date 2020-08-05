package healthconnect.services.implementation;

import healthconnect.models.entity.Department;
import healthconnect.models.service.DepartmentServiceModel;
import healthconnect.models.view.DepartmentViewModel;
import healthconnect.repositories.DepartmentRepository;
import healthconnect.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentViewModel> getAllDepartments() {
       List<Department> departments = this.departmentRepository.findAll();
       List<DepartmentViewModel> viewDepartments = new ArrayList<>();
        for (Department department : departments) {
            viewDepartments.add(this.modelMapper.
                    map(department, DepartmentViewModel.class));
        }

        return viewDepartments;
    }

    @Override
    public DepartmentServiceModel getDepartmentByName(String name) {

        return this.modelMapper.map
                (this.departmentRepository.findDepartmentByName(name),
                        DepartmentServiceModel.class);
    }
}


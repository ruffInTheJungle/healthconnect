package healthconnect.services.implementation;

import healthconnect.models.entity.Department;
import healthconnect.models.service.DepartmentServiceModel;
import healthconnect.models.view.DepartmentViewModel;
import healthconnect.repositories.DepartmentRepository;
import healthconnect.services.DepartmentService;
import healthconnect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, UserService userService, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.userService = userService;
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

    @Override
    public void initDepartments() {
        if (this.departmentRepository.count() == 0) {

            Department cardiology = new Department("Cardiology",
                    "Our Division tirelessly pursues a trajectory of excellence.\n" +
                            "On an annual basis, we rank among the top 10 cardiology and heart surgery programs in the nation.\n" +
                            "We believe that our academic trajectory, clinical expertise and comprehensive education programs are unparalleled.\n" +
                            "Scholarship, leadership and excellence with humanity mirror our DNA.",
                    "assets/img/departments-1.jpg",
                    new ArrayList<>());
            cardiology.getDoctors().add(this.userService.getUserByUsername("doctor@abv.bg"));

            Department neurology = new Department("Neurology",
                    "The Department of Neurology at our hospital facilitates the latest diagnosis and treatment of various diseases\n" +
                            "of the central and peripheral nervous system, with emphasis on the following major diseases: cerebrovascular diseases,\n" +
                            "autoimmune diseases, degenerative diseases, diseases of the peripheral nervous system, etc.",
                    "assets/img/departments-2.jpg",
                    new ArrayList<>());
            neurology.getDoctors().add(this.userService.getUserByUsername("doctor@abv.bg"));

            Department orthopedics = new Department("Orthopedics",
                    "Our orthopedic and traumatology department diagnoses and treats all types of diseases, which affect the musculoskeletal locomotion system.\n" +
                            "We are equipped with ultra high-tech equipment to conduct arthroscopic surgery and computer assisted\n" +
                            "surgery to correct osteoarthritis, damaged meniscus, joints diseases, and joint traumas.",
                    "assets/img/departments-3.jpg",
                    new ArrayList<>());
            orthopedics.getDoctors().add(this.userService.getUserByUsername("doctor@abv.bg"));

            Department pediatrics = new Department("Pediatrics",
                    "Our multi-disciplinary pediatric department provides comprehensive medical services with a special focus upon\n" +
                            "pulmonology, gastroenterology, neurology, nephrology, rheumatology, endocrinology and diabetes.",
                    "assets/img/departments-4.jpg",
                    new ArrayList<>());
            pediatrics.getDoctors().add(this.userService.getUserByUsername("doctor@abv.bg"));

            Department medicalImaging = new Department("Medical Imaging",
                    "The philosophy in the Medical imaging department at our hospital is entirely oriented towards the patient.\n" +
                            "We take care of the patient as our primary task, each examination is interpreted by professional medical imaging specialist and the results are discussed together with the patient.\n" +
                            "Our goal is for every patient to leave the department with a prepared result or consulted when necessary.",
                    "assets/img/departments-4.jpg",
                    new ArrayList<>());
            medicalImaging.getDoctors().add(this.userService.getUserByUsername("doctor@abv.bg"));

            this.departmentRepository.saveAll(List.of(cardiology, neurology, orthopedics, pediatrics, medicalImaging));
        }
    }
}


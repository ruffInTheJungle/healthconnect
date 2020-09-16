package healthconnect;

import healthconnect.services.AppointmentService;
import healthconnect.services.DepartmentService;
import healthconnect.services.StatusService;
import healthconnect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInit implements CommandLineRunner {

    private final UserService userService;
    private final StatusService statusService;
    private final DepartmentService departmentService;
    private final AppointmentService appointmentService;

    @Autowired
    public ApplicationInit(UserService userService, StatusService statusService, DepartmentService departmentService, AppointmentService appointmentService) {
        this.userService = userService;
        this.statusService = statusService;
        this.departmentService = departmentService;
        this.appointmentService = appointmentService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.initUsers();
        this.statusService.initStatuses();
        this.departmentService.initDepartments();
        this.appointmentService.initAppointments();
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        if (statusRepository.count() == 0) {
//            Status requested = new Status("REQUESTED", "Your appointment has been " +
//                    "requested. Up to 24 hours after the request our team will contact you to arrange the details.");
//            Status confirmed = new Status("CONFIRMED", "Your appointment has been" +
//                    " confirmed. Our specialist will be waiting for you at the confirmed day and time." +
//                    "You could always refer to your Appointments section for details.");
//            Status archived = new Status("ARCHIVED", "Your appointment has been archived.");
//            this.statusRepository.saveAll(List.of(requested, confirmed, archived));
//
//            if (this.roleRepository.count() == 0) {
//                User patient = new User();
//                patient.setUsername("patient@abv.bg");
//                patient.setPassword(passwordEncoder.encode("patient"));
//                patient.setEnabled(true);
//
//                Role rolePatient = new Role();
//                rolePatient.setName("ROLE_PATIENT");
//                rolePatient.setUser(patient);
//                patient.setRoles(List.of(rolePatient));
//                patient.setFirstName("John");
//                patient.setLastName("Smith");
//                patient.setEnabled(true);
//                patient.setRegistrationDate(LocalDateTime.now());
//                patient.setBirthday(LocalDate.of(1991, 04, 05));
//                patient.setSalutation("Mr.");
//
//                userRepository.save(patient);
//
//                User doctor = new User();
//                doctor.setUsername("doctor@abv.bg");
//                doctor.setPassword(passwordEncoder.encode("doctor"));
//                doctor.setEnabled(true);
//                doctor.setFirstName("Peter");
//                doctor.setLastName("Griffin");
//                doctor.setEnabled(true);
//                doctor.setRegistrationDate(LocalDateTime.now());
//                doctor.setBirthday(LocalDate.of(1985, 11, 02));
//                doctor.setSalutation("Dr.");
//
//                Role roleDoctor = new Role();
//                roleDoctor.setName("ROLE_DOCTOR");
//                roleDoctor.setUser(doctor);
//                doctor.setRoles(List.of(roleDoctor));
//
//                userRepository.save(doctor);
//
//
//                User admin = new User();
//                admin.setUsername("admin@abv.bg");
//                admin.setPassword(passwordEncoder.encode("admin"));
//                admin.setEnabled(true);
//                admin.setEnabled(true);
//                admin.setFirstName("Admin");
//                admin.setLastName("Adminov");
//                admin.setEnabled(true);
//                admin.setRegistrationDate(LocalDateTime.now());
//                admin.setBirthday(LocalDate.of(681, 01, 01));
//                admin.setSalutation("Master");
//
//                Role adminPatientAuthorityEntity = new Role();
//                adminPatientAuthorityEntity.setName("ROLE_PATIENT");
//                adminPatientAuthorityEntity.setUser(admin);
//
//                Role adminDoctorAuthorityEntity = new Role();
//                adminDoctorAuthorityEntity.setName("ROLE_DOCTOR");
//                adminDoctorAuthorityEntity.setUser(admin);
//
//                Role adminAdminAuthorityEntity = new Role();
//                adminAdminAuthorityEntity.setName("ROLE_ADMIN");
//                adminAdminAuthorityEntity.setUser(admin);
//
//                admin.setRoles(List.of(adminDoctorAuthorityEntity, adminPatientAuthorityEntity,
//                        adminAdminAuthorityEntity));
//
//                userRepository.save(admin);
//
//                if (this.appointmentRepository.count() == 0) {
//                    Appointment firstAppointment = new Appointment(doctor, patient,
//                            LocalDateTime.now(), this.statusRepository.findStatusByName("REQUESTED"));
//                    Appointment secondAppointment = new Appointment(doctor, patient,
//                            LocalDateTime.now(), this.statusRepository.findStatusByName("CONFIRMED"));
//                    Appointment thirdAppointment = new Appointment(doctor, patient,
//                            LocalDateTime.now(), this.statusRepository.findStatusByName("ARCHIVED"));
//
//                    this.appointmentRepository.saveAll(List.of(firstAppointment, secondAppointment, thirdAppointment));
//
//                    this.prescriptionRepository.save(
//                            new Prescription(doctor, patient, LocalDate.now(),
//                                    "MNOGO DOBRO LEKARSTWO TI DAWAM!!", thirdAppointment));
//                }
//            }
//        }
//
//        if (this.departmentRepository.count() == 0) {
//
//            Department cardiology = new Department("Cardiology",
//                    "Our Division tirelessly pursues a trajectory of excellence.\n" +
//                            "On an annual basis, we rank among the top 10 cardiology and heart surgery programs in the nation.\n" +
//                            "We believe that our academic trajectory, clinical expertise and comprehensive education programs are unparalleled.\n" +
//                            "Scholarship, leadership and excellence with humanity mirror our DNA.",
//                    "assets/img/departments-1.jpg",
//                    new ArrayList<>());
//            cardiology.getDoctors().add(this.userRepository.getOne((long) 2));
//
//            Department neurology = new Department("Neurology",
//                    "The Department of Neurology at our hospital facilitates the latest diagnosis and treatment of various diseases\n" +
//                            "of the central and peripheral nervous system, with emphasis on the following major diseases: cerebrovascular diseases,\n" +
//                            "autoimmune diseases, degenerative diseases, diseases of the peripheral nervous system, etc.",
//                    "assets/img/departments-2.jpg",
//                    new ArrayList<>());
//            neurology.getDoctors().add(this.userRepository.getOne((long) 2));
//
//            Department orthopedics = new Department("Orthopedics",
//                    "Our orthopedic and traumatology department diagnoses and treats all types of diseases, which affect the musculoskeletal locomotion system.\n" +
//                            "We are equipped with ultra high-tech equipment to conduct arthroscopic surgery and computer assisted\n" +
//                            "surgery to correct osteoarthritis, damaged meniscus, joints diseases, and joint traumas.",
//                    "assets/img/departments-3.jpg",
//                    new ArrayList<>());
//            orthopedics.getDoctors().add(this.userRepository.getOne((long) 2));
//
//            Department pediatrics = new Department("Pediatrics",
//                    "Our multi-disciplinary pediatric department provides comprehensive medical services with a special focus upon\n" +
//                            "pulmonology, gastroenterology, neurology, nephrology, rheumatology, endocrinology and diabetes.",
//                    "assets/img/departments-4.jpg",
//                    new ArrayList<>());
//            pediatrics.getDoctors().add(this.userRepository.getOne((long) 2));
//
//            Department medicalImaging = new Department("Medical Imaging",
//                    "The philosophy in the Medical imaging department at our hospital is entirely oriented towards the patient.\n" +
//                            "We take care of the patient as our primary task, each examination is interpreted by professional medical imaging specialist and the results are discussed together with the patient.\n" +
//                            "Our goal is for every patient to leave the department with a prepared result or consulted when necessary.",
//                    "assets/img/departments-4.jpg",
//                    new ArrayList<>());
//            medicalImaging.getDoctors().add(this.userRepository.getOne((long) 2));
//            medicalImaging.getDoctors().add(this.userRepository.getOne((long) 2));
//
//            this.departmentRepository.saveAll(List.of(cardiology, neurology, orthopedics, pediatrics, medicalImaging));
//        }
//
//    }
}

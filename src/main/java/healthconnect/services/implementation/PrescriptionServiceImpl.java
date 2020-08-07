package healthconnect.services.implementation;

import healthconnect.models.binding.PrescriptionBindingModel;
import healthconnect.models.binding.PrescriptionEditBindingModel;
import healthconnect.models.entity.Prescription;
import healthconnect.models.service.PrescriptionServiceModel;
import healthconnect.repositories.PrescriptionRepository;
import healthconnect.services.AppointmentService;
import healthconnect.services.PrescriptionService;
import healthconnect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final UserService userService;
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, UserService userService, AppointmentService appointmentService, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PrescriptionServiceModel> getPrescriptionsForUser(String patientName) {
        List<PrescriptionServiceModel> prescriptions = new ArrayList<>();
        for (Prescription prescription : this.prescriptionRepository.findAllByPrescribeToUsername(patientName)) {
            prescriptions.add(this.modelMapper.map(prescription, PrescriptionServiceModel.class));
        }

        return prescriptions;
    }

    @Override
    public PrescriptionServiceModel getPrescriptionWithId(Long id) {

        return this.modelMapper.map(this.prescriptionRepository.findById(id).orElse(null), PrescriptionServiceModel.class);
    }

    @Override
    public void issuePrescription(PrescriptionBindingModel prescriptionBindingModel) {
            Prescription prescription = new Prescription();

            prescription.setPrescribeTo(this.userService.getUserById(prescriptionBindingModel.getPatientId()));
            prescription.setPrescribedBy(this.userService.getUserByUsername(prescriptionBindingModel.getDoctorUsername()));
            prescription.setDate(LocalDate.now());
            prescription.setPrescriptionNotes(prescriptionBindingModel.getNotes());
            prescription.setAppointment(this.appointmentService.getAppointmentById(prescriptionBindingModel.getAppointmentId()));

            int a = 5;

            this.prescriptionRepository.save(prescription);
    }

    @Override
    public List<PrescriptionServiceModel> getPrescriptionsForDoctor(String doctorName) {
        List<PrescriptionServiceModel> prescriptions = new ArrayList<>();
        for (Prescription prescription : this.prescriptionRepository.findAllByPrescribedByUsername(doctorName)) {
            prescriptions.add(this.modelMapper.map(prescription, PrescriptionServiceModel.class));
        }

        return prescriptions;
    }

    @Override
    public void editPrescription(PrescriptionEditBindingModel prescriptionEditBindingModel) {

        Prescription prescription = this.prescriptionRepository.findOneById(prescriptionEditBindingModel.getPrescriptionId());
        prescription.setPrescriptionNotes(prescriptionEditBindingModel.getNotes());
        prescription.setDate(LocalDate.now());
        this.prescriptionRepository.save(prescription);
    }
}

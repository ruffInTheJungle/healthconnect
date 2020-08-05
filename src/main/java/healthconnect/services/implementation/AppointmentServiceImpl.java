package healthconnect.services.implementation;

import healthconnect.models.entity.Appointment;
import healthconnect.models.service.AppointmentServiceModel;
import healthconnect.repositories.AppointmentRepository;
import healthconnect.services.AppointmentService;
import healthconnect.services.StatusService;
import healthconnect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final StatusService statusService;
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserService userService, StatusService statusService, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userService = userService;
        this.statusService = statusService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AppointmentServiceModel> getAppointmentsForUserWithUsername(String patientName) {

        List<AppointmentServiceModel> appointments = new ArrayList<>();


        for (Appointment appointment : this.appointmentRepository.findAllByPatientUsername(patientName)) {

            appointments.add(this.modelMapper.map(appointment, AppointmentServiceModel.class));
        }

        return appointments;
    }

    @Override
    public void createAppointmentRequest(String doctorUsername, String patientName) {

        Appointment appointment = new Appointment(
                this.userService.getUserByUsername(doctorUsername), this.userService.getUserByUsername(patientName),
                null, this.statusService.getRequestedStatus());

        this.appointmentRepository.save(appointment);

    }

    @Override
    public List<AppointmentServiceModel> getAllRequestedAppointmentsByDoctor(String username) {
        List<AppointmentServiceModel> requestedAppointments = new ArrayList<>();
        for (Appointment appointment : this.appointmentRepository.findAllByDoctorUsernameAndStatusName(username, "REQUESTED")) {
            requestedAppointments.add(this.modelMapper.map(appointment, AppointmentServiceModel.class));
        }
        return requestedAppointments;
    }

    @Override
    public List<AppointmentServiceModel> getAllConfirmedAppointmentsByDoctor(String username) {
        List<AppointmentServiceModel> requestedAppointments = new ArrayList<>();
        for (Appointment appointment : this.appointmentRepository.findAllByDoctorUsernameAndStatusName(username, "CONFIRMED")) {
            requestedAppointments.add(this.modelMapper.map(appointment, AppointmentServiceModel.class));
        }
        return requestedAppointments;
    }

    @Override
    public List<AppointmentServiceModel> getAllArchivedAppointmentsByDoctor(String username) {
        List<AppointmentServiceModel> requestedAppointments = new ArrayList<>();
        for (Appointment appointment : this.appointmentRepository.findAllByDoctorUsernameAndStatusName(username, "ARCHIVED")) {
            requestedAppointments.add(this.modelMapper.map(appointment, AppointmentServiceModel.class));
        }
        return requestedAppointments;
    }

}

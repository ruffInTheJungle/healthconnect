package healthconnect.web.controllers;

import healthconnect.models.binding.UserRegistrationBindingModel;
import healthconnect.models.service.AppointmentServiceModel;
import healthconnect.models.service.UserServiceModel;
import healthconnect.models.view.AppointmentViewModel;
import healthconnect.models.view.DoctorViewModelMakeAppointment;
import healthconnect.services.AppointmentService;
import healthconnect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AppointmentController(AppointmentService appointmentService, UserService userService, ModelMapper modelMapper) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/appointments")
    public String getAllAppointmentsForPatient(Model model) {
        String patientName = SecurityContextHolder.getContext().getAuthentication().getName();

        List<AppointmentViewModel> appointments = new ArrayList<>();

        for (AppointmentServiceModel appointmentServiceModel : this.appointmentService.getAppointmentsForUserWithUsername(patientName)) {
            AppointmentViewModel appointmentViewModel = this.modelMapper.map(appointmentServiceModel, AppointmentViewModel.class);
            String date = getString(appointmentViewModel);
            appointmentViewModel.setAppointmentTime(date);
            appointments.add(appointmentViewModel);
        }
        model.addAttribute("appointments", appointments);


        return "appointments";
    }

    @GetMapping("/appointments/make-appointment")
    public String makeAppointment(Model model) {

        List<DoctorViewModelMakeAppointment> doctors = new ArrayList<>();

        for (UserServiceModel doctor : this.userService.getAllDoctors()) {
            doctors.add(this.modelMapper.map(doctor, DoctorViewModelMakeAppointment.class));
        }

        model.addAttribute("doctors", doctors);

        return "make-appointment";
    }

    @PostMapping("/appointments/make-appointment")
    public String makeAppointmentPost(@ModelAttribute("doctor")
                                              String doctor) {

        String patientName = SecurityContextHolder.getContext().getAuthentication().getName();

        this.appointmentService.createAppointmentRequest(doctor, patientName);

        return "redirect:/appointments";
    }


    private String getString(AppointmentViewModel appointmentViewModel) {
        if (appointmentViewModel.getAppointmentTime() == null) {
            return "TO BE CONFIRMED";
        }
        String date = appointmentViewModel.getAppointmentTime();
        String[] tokens = date.split("T");
        tokens[1] = tokens[1].substring(0, 5);
        date = tokens[0] + " Time: " + tokens[1];
        return date;
    }
}

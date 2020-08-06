package healthconnect.web.controllers;

import healthconnect.models.binding.PrescriptionBindingModel;
import healthconnect.models.service.PrescriptionServiceModel;
import healthconnect.models.view.PrescriptionViewModel;
import healthconnect.services.AppointmentService;
import healthconnect.services.PrescriptionService;
import healthconnect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final UserService userService;
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;

    public PrescriptionController(PrescriptionService prescriptionService, ModelMapper modelMapper, UserService userService, AppointmentService appointmentService) {
        this.prescriptionService = prescriptionService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/prescriptions")
    public String getAllAppointmentsForPatient(Model model) {
        String patientName = SecurityContextHolder.getContext().getAuthentication().getName();

        List<PrescriptionViewModel> prescriptions = new ArrayList<>();

        for (PrescriptionServiceModel prescriptionServiceModel : this.prescriptionService.getPrescriptionsForUser(patientName)) {
            prescriptions.add(this.modelMapper.map(prescriptionServiceModel, PrescriptionViewModel.class));
        }

        model.addAttribute("prescriptions", prescriptions);

        return "prescriptions";
    }

    @RequestMapping(value = "/prescriptions/prescription")
    public String getDepartmentDoctors(@RequestParam("id") Long id, Model model) {


        PrescriptionViewModel prescription = this.modelMapper
                .map(this.prescriptionService.getPrescriptionWithId(id), PrescriptionViewModel.class);

        model.addAttribute("prescription", prescription);

        return "prescription";
    }

    @RequestMapping("/doctor/issuePrescription")
    public String archiveAppointmentAndIssuePrescription(@RequestParam(name = "appointmentId") String appointmentId,
                                                         @RequestParam Long patientId, Model model) {


        model.addAttribute("appointmentId", appointmentId);
        model.addAttribute("patientId", patientId);
        model.addAttribute("nameOfUser", this.userService.getUserFullName(patientId));
        this.appointmentService.archiveAppointment(Long.parseLong(appointmentId));

        return "doctors/prescription-form";
    }

    @PostMapping("/doctor/issuePrescription")
    public String issuePrescription(@ModelAttribute("prescriptionBindingModel") PrescriptionBindingModel prescriptionBindingModel) {

        prescriptionBindingModel.setDoctorUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        this.prescriptionService.issuePrescription(prescriptionBindingModel);

        return "redirect:/doctor/appointments/archived";
    }

}

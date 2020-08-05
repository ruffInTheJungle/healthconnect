package healthconnect.web.controllers;

import healthconnect.models.service.AppointmentServiceModel;
import healthconnect.models.service.PrescriptionServiceModel;
import healthconnect.models.view.AppointmentViewModel;
import healthconnect.models.view.DepartmentViewModel;
import healthconnect.models.view.PrescriptionViewModel;
import healthconnect.services.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final ModelMapper modelMapper;

    public PrescriptionController(PrescriptionService prescriptionService, ModelMapper modelMapper) {
        this.prescriptionService = prescriptionService;
        this.modelMapper = modelMapper;
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


}

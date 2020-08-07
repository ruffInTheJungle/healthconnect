package healthconnect.services;

import healthconnect.models.binding.PrescriptionBindingModel;
import healthconnect.models.binding.PrescriptionEditBindingModel;
import healthconnect.models.service.PrescriptionServiceModel;

import java.util.List;

public interface PrescriptionService {
    List<PrescriptionServiceModel> getPrescriptionsForUser(String patientName);

    PrescriptionServiceModel getPrescriptionWithId(Long id);

    void issuePrescription(PrescriptionBindingModel prescriptionBindingModel);

    List<PrescriptionServiceModel> getPrescriptionsForDoctor(String doctorName);

    void editPrescription(PrescriptionEditBindingModel prescriptionEditBindingModel);
}

package healthconnect.services;

import healthconnect.models.service.PrescriptionServiceModel;

import java.util.List;

public interface PrescriptionService {
    List<PrescriptionServiceModel> getPrescriptionsForUser(String patientName);

    PrescriptionServiceModel getPrescriptionWithId(Long id);
}

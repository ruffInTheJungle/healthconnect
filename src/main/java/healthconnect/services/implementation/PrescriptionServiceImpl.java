package healthconnect.services.implementation;

import healthconnect.models.entity.Prescription;
import healthconnect.models.service.PrescriptionServiceModel;
import healthconnect.repositories.PrescriptionRepository;
import healthconnect.services.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final ModelMapper modelMapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, ModelMapper modelMapper) {
        this.prescriptionRepository = prescriptionRepository;
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
}

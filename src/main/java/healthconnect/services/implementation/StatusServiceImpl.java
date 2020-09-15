package healthconnect.services.implementation;

import healthconnect.models.entity.Status;
import healthconnect.models.service.StatusServiceModel;
import healthconnect.repositories.StatusRepository;
import healthconnect.services.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final ModelMapper modelMapper;

    public StatusServiceImpl(StatusRepository statusRepository, ModelMapper modelMapper) {
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Status getRequestedStatus () {
        return this.statusRepository.findStatusByName("REQUESTED");
    }

    @Override
    public Status getConfirmedStatus() {
         return this.statusRepository.findStatusByName("CONFIRMED");
    }

    @Override
    public Status getArchivedStatus() {
        return this.statusRepository.findStatusByName("ARCHIVED");
    }

    @Override
    public List<StatusServiceModel> getAllStatuses() {

        List <StatusServiceModel> statuses = new ArrayList<>();

        for (Status status : this.statusRepository.findAll()) {
            statuses.add(this.modelMapper.map(status, StatusServiceModel.class));
        }

        return statuses;
    }
}

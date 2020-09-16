package healthconnect.services;

import healthconnect.models.entity.Status;
import healthconnect.models.service.StatusServiceModel;

import java.util.List;

public interface StatusService {

    Status getRequestedStatus ();

    Status getConfirmedStatus ();

    List<StatusServiceModel> getAllStatuses();

    Status getArchivedStatus();

    void initStatuses();

}

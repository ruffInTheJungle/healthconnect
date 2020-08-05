package healthconnect.services;

import healthconnect.models.entity.User;
import healthconnect.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    String getGreeting (String username);

    void registerPatient(UserServiceModel userServiceModel);

    boolean checkIfUsernameExists(String username);

    List<UserServiceModel> getAllDoctors();

    User getUserByUsername(String username);

    String getDoctorAppointmentsGreeting (String username);
}

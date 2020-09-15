package healthconnect.services;

import healthconnect.models.entity.Role;

public interface RoleService {

    void deleteCurrentRolesForUser(String username);

    Role getNewRole();

}

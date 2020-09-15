package healthconnect.services.implementation;

import healthconnect.models.entity.Role;
import healthconnect.repositories.RoleRepository;
import healthconnect.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void deleteCurrentRolesForUser(String username) {

        List<Role> rolesOfUser = this.roleRepository.findAllByUserUsername(username);

        for (Role role : rolesOfUser) {
            this.roleRepository.deleteOneById(role.getId());
        }

    }

    @Override
    public Role getNewRole() {

        return new Role();
    }
}

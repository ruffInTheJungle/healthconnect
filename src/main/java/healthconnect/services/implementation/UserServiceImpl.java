package healthconnect.services.implementation;

import healthconnect.models.entity.Role;
import healthconnect.models.entity.User;
import healthconnect.models.service.RoleServiceModel;
import healthconnect.models.service.UserServiceModel;
import healthconnect.repositories.UserRepository;
import healthconnect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    private UserDetails map(User userEntity) {
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.
                        getRoles().
                        stream().
                        map(this::map).
                        collect(Collectors.toList())
        );
    }

    private GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(role.getName());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userEntityOpt = userRepository.findByUsername(username);
        return userEntityOpt.
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    @Override
    public String getGreeting(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        String greeting = "";
        if (user != null) {
            greeting = "Welcome " + user.getSalutation() + " " + user.getLastName();
        }
        return greeting;
    }

    @Override
    public void registerPatient(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        Role rolePatient = new Role();
        rolePatient.setName("ROLE_PATIENT");
        rolePatient.setUser(user);
        user.setRoles(List.of(rolePatient));
        user.setRegistrationDate(LocalDateTime.now());
        user.setEnabled(true);

        this.userRepository.save(user);
    }

    @Override
    public boolean checkIfUsernameExists(String username) {
        return this.userRepository.findByUsername(username).orElse(null) != null;
    }

    @Override
    public List<UserServiceModel> getAllDoctors() {
        List<UserServiceModel> users = new ArrayList<>();

        for (User user : this.userRepository.findAll()) {
            for (Role role : user.getRoles()) {
                if (role.getName().equals("ROLE_DOCTOR")) {
                    users.add(this.modelMapper.map(user, UserServiceModel.class));
                    break;
                }
            }
        }


        for (int i = 0; i < users.size(); i++) {
            for (RoleServiceModel role : users.get(i).getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")) {
                    users.remove(i);
                    i--;
                    break;
                }
            }

        }

        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public String getDoctorAppointmentsGreeting(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);

        String greeting = "";
        if (user != null) {
            greeting = user.getSalutation() + " " + user.getLastName() + "`s Appointments";
        }
        return greeting;
    }

    @Override
    public String getUserFullName(Long patientId) {
        User user = this.userRepository.findOneById(patientId);
        return user.getSalutation() + " " + user.getFirstName() + " " + user.getLastName();
    }

    @Override
    public User getUserById(Long patientId) {
        return this.userRepository.findOneById(patientId);
    }
}

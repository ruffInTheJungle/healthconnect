package healthconnect.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "users")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 30, message = "INCORRECT_USERNAME_LENGTH")
    private String username;
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private String password;
    @Column(name = "first_name", nullable = false)
    @Size(min = 3, max = 20, message = "INCORRECT_FIRST_NAME_LENGTH")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @Size(min = 3, max = 20, message = "INCORRECT_LAST_NAME_LENGTH")
    private String lastName;
    private String salutation;
    private LocalDate  birthday;
    @NotNull(message = "INVALID_ROLE")
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Role> roles;
    @NotNull
    @PastOrPresent
    private LocalDateTime registrationDate;
    private Double rating;
    private boolean enabled;


}

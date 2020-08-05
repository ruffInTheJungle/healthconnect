package healthconnect.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import static healthconnect.messages.ValidationErrorMessages.*;


@Entity
@Table(name = "departments")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department extends BaseEntity{

    //TODO HANDLE IMAGE URL VALIDATION


    @NotNull
    @Size(max = 45)
    private String name;
    @NotNull
    @Column(length = 4500)
    private String description;
    @NotNull
    private String imageURL;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "departments_doctors",
            joinColumns = @JoinColumn(name="department_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="doctor_id", referencedColumnName = "id"))
    private List <User> doctors;
}

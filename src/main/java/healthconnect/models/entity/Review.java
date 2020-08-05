package healthconnect.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static healthconnect.messages.ValidationErrorMessages.*;

@Entity
@Table(name = "reviews")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private User receiver;
    @Min(value = 1, message = INVALID_GRADE)
    @Max(value = 5, message = INVALID_GRADE)
    private Double grade;

}

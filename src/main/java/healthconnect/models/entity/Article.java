package healthconnect.models.entity;
import static healthconnect.messages.ValidationErrorMessages.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "articles")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Article extends BaseEntity{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;
    @NotNull
    @Size(min = 3, max = 200, message = INVALID_TITLE_LENGTH)
    private String title;
    private String subTitle;
    @Column(columnDefinition = "TEXT")
    @Size(min = 400, message = INVALID_ARTICLE_TEXT_LENGTH)
    private String text;
    private String imageURL;

}

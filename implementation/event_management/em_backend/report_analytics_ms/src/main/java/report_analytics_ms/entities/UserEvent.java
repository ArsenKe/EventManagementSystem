package report_analytics_ms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_event")
public class UserEvent {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy = "uuid", name = "generator")
    private String userEventId;
    private boolean confirm = false;
    private boolean marked = false;
    private boolean attendance = false;
    private String feedback;

    private String tags;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    public UserEvent(boolean confirm, boolean marked, String tags, User user, Event event) {
        this.confirm = confirm;
        this.marked = marked;
        this.tags = tags;
        this.user = user;
        this.event = event;
    }

    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    private Event event;
}

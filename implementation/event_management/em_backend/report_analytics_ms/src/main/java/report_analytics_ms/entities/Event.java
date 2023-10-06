package report_analytics_ms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy = "uuid", name = "generator")
    private String id;
    private String eventName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.eventName = eventName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}

package calender_export_ms.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter
@XmlRootElement
@NoArgsConstructor
public class CalendarExportResponse implements Serializable {
    private String eventId;
    private String eventName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDateTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDateTime;
    private List<String> tags = new ArrayList<>();
    private boolean confirmed;
    private boolean marked;

    @XmlElement(name = "event-id")
    public String getEventId() {
        return eventId;
    }

    @XmlElement(name = "event-name")
    public String getEventName() {
        return eventName;
    }

    @XmlElement(name = "stdate")
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    @XmlElement(name = "enddate")
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @XmlElement(name = "tags")
    public List<String> getTags() {
        return tags;
    }

    @XmlElement(name = "confirm")
    public boolean isConfirmed() {
        return confirmed;
    }

    @XmlElement(name = "marked")
    public boolean isMarked() {
        return marked;
    }
}

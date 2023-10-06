package calender_export_ms.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@XmlSeeAlso({CalendarExportResponse.class})
public class XmlBody {
    List<CalendarExportResponse> calendarExportResponses = new ArrayList<>();

    @XmlElement
    public List<CalendarExportResponse> getCalendarExportResponses() {
        return calendarExportResponses;
    }
}

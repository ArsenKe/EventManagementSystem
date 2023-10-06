package calender_export_ms.services;

import calender_export_ms.entities.Event;
import calender_export_ms.entities.User;
import calender_export_ms.entities.UserEvent;
import calender_export_ms.exceptions.UserNotFoundException;
import calender_export_ms.models.CalendarExportResponse;
import calender_export_ms.models.Format;
import calender_export_ms.models.XmlBody;
import calender_export_ms.repositories.EventRepository;
import calender_export_ms.repositories.UserEventRepository;
import calender_export_ms.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Comment;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Summary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Service
@Slf4j
public class CalendarExportService {

    private final UserRepository userRepository;
    private final UserEventRepository userEventRepository;
    private final EventRepository eventRepository;


    @PostConstruct
    public void init(){}

    // Query events and export based on exportType and format
    public ResponseEntity queryEvents(long userId, String exportType, Format format, Pageable page) throws IOException, JAXBException {
        User user = getUser(userId);
        Page<UserEvent> userEventPage = getUserEventPage(user, exportType, page);
        Page<CalendarExportResponse> calendarExportResponsePage = getCalendarExportResponse(userEventPage);
        if(format == null){
            return ResponseEntity.ok(calendarExportResponsePage);
        }else if(format.equals(Format.ICS)){
            return getCSIFile(calendarExportResponsePage);
        } else if (format.equals(Format.XML)){
            return getXMLFile(calendarExportResponsePage);
        }else if (format.equals(Format.JSON)){
            return getJsonFile(calendarExportResponsePage);
        } else {
            return ResponseEntity.ok().build();
        }

    }
    // Generate JSON file from calendar export response
    private ResponseEntity getJsonFile(Page<CalendarExportResponse> calendarExportResponsePage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = mapper.writeValueAsBytes(calendarExportResponsePage.getContent());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "events.json");

        return ResponseEntity
                .ok()
                .contentLength(bytes.length)
                .headers(headers)
                .body(new InputStreamResource(new ByteArrayInputStream(bytes)));
    }

    // Generate XML file from calendar export response
    public ResponseEntity getXMLFile(Page<CalendarExportResponse> calendarExportResponsePage) throws JAXBException {
        JAXBContext jaxbContext;
        StringWriter writer = new StringWriter();


        jaxbContext = JAXBContext.newInstance(XmlBody.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(new XmlBody(calendarExportResponsePage.getContent()), writer);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.set("Content-Disposition", "attachment; filename=events.xml");

        return ResponseEntity.ok().headers(headers).body(writer.toString());
    }

    // Generate ICS file from calendar export response
    private ResponseEntity getCSIFile(Page<CalendarExportResponse> calendarExportResponsePage) throws IOException {
        CalendarOutputter outputter = new CalendarOutputter();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        outputter.output(getCalendar(calendarExportResponsePage), bout);

        // Set the appropriate HTTP headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/calendar"));
        headers.setContentDispositionFormData("attachment", "event.ics");

        // Create an InputStreamResource from the generated ICS file content
        InputStream inputStream = new ByteArrayInputStream(bout.toByteArray());
        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    // Generate Calendar object from calendar export response
    private Calendar getCalendar(Page<CalendarExportResponse> calendarExportResponsePage){
        List<VEvent> vEvents = calendarExportResponsePage.getContent().stream().map(calendarExportResponse -> {
            List<Property> propertyList = new ArrayList<>();
            propertyList.add(new DtStart<>(calendarExportResponse.getStartDateTime()));
            propertyList.add(new DtEnd<>(calendarExportResponse.getEndDateTime()));
            propertyList.add(new Summary(calendarExportResponse.getEventName()));
            propertyList.add(new Comment(calendarExportResponse.getTags().toString()));

            VEvent vEvent = new VEvent();
            vEvent.setPropertyList(new PropertyList(propertyList));
            return vEvent;
        }).collect(Collectors.toList());

        List<CalendarComponent> calendarComponents = new ArrayList<>(vEvents);
        ComponentList<CalendarComponent> calendarComponentComponentList = new ComponentList<>(calendarComponents);

        Calendar calendar = new Calendar();
        calendar.setComponentList(calendarComponentComponentList);
        return calendar;
    }

    // Map UserEvent objects to CalendarExportResponse objects
    private Page<CalendarExportResponse> getCalendarExportResponse(Page<UserEvent> userEventPage){
        return userEventPage.map(userEvent -> {
            Event event = userEvent.getEvent();
            CalendarExportResponse calendarExportResponse = new CalendarExportResponse();
            calendarExportResponse.setEventId(event.getId());
            calendarExportResponse.setEventName(event.getEventName());
            calendarExportResponse.setStartDateTime(event.getStartDateTime());
            calendarExportResponse.setEndDateTime(event.getEndDateTime());
            calendarExportResponse.setTags(Collections.singletonList(userEvent.getTags()));
            calendarExportResponse.setConfirmed(userEvent.isConfirm());
            calendarExportResponse.setMarked(userEvent.isMarked());
            return calendarExportResponse;
        });
    }

    private List<String> getTagList(String tagString){
        if(tagString != null){
            String newString = tagString.replaceAll("[\\[\\]]", "").replaceAll(" ","");
            return Arrays.asList(newString.split(","));
        }else {
            return Collections.emptyList();
        }
    }
    // Get UserEvent page based on exportType
    private Page<UserEvent> getUserEventPage(User user, String exportType, Pageable page) {
        if (exportType.equals("tagged")) {
            return userEventRepository.findAllByUserAndTagsNotNull(user, page);
        } else if (exportType.equals("confirmed_attendance")) {
            return userEventRepository.findAllByUserAndConfirm(user, true, page);
        } else if (exportType.equals("marked")) {
            return userEventRepository.findAllByUserAndMarked(user, true, page);
        } else {
            return Page.empty();
        }
    }

        private User getUser(long userId){
            return userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.getUserNotFoundException());
        }

    // Overloaded method for testing purposes
    public Object queryEvents(long l, Object o) {
        return o;
    }
}
package calender_export_ms.services;

import calender_export_ms.entities.Event;
import calender_export_ms.entities.User;
import calender_export_ms.entities.UserEvent;
import calender_export_ms.models.CalendarExportResponse;
import calender_export_ms.repositories.UserEventRepository;
import calender_export_ms.repositories.UserRepository;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalenderExportServiceTest {

    @Mock
    private UserEventRepository userEventRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CalendarExportService calendarExportService;

    @Test
    public void getXmlFile() throws JAXBException, IOException {
        User user = getUser();
        UserEvent userEvent = getUserEvent();
        PageRequest page = PageRequest.of(0, 10);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userEventRepository.findAllByUserAndTagsNotNull(user, page)).thenReturn(new PageImpl<>(Arrays.asList(userEvent), page, 10));
        calendarExportService.queryEvents(1L, "tagged", null, page);
        verify(userEventRepository).findAllByUserAndTagsNotNull(user, page);
//        assertEquals(calendarExportService.queryEvents(1L, "tagged", null, page), ResponseEntity.ok(new PageImpl<>(Arrays.asList(getCalendarExportResponse(), page, 10))));
    }



    private User getUser(){
        return new User(1L,"init_user");
    }

    private Event getEvent(){
        return new Event("1","init_event_1", LocalDateTime.now().plusDays(1).plusHours(1), LocalDateTime.now().plusDays(1).plusHours(1).plusMinutes(30));
    }

    private UserEvent getUserEvent(){
        return new UserEvent("1",true, false, (new ArrayList<>(Arrays.asList("sport", "food"))).toString(), getUser(), getEvent());
    }

    private CalendarExportResponse getCalendarExportResponse(){
        CalendarExportResponse calendarExportResponse = new CalendarExportResponse();
        calendarExportResponse.setEventId("1");
        calendarExportResponse.setEventName("init_event_1");
        calendarExportResponse.setStartDateTime(LocalDateTime.now().plusDays(1).plusHours(1));
        calendarExportResponse.setEndDateTime(LocalDateTime.now().plusDays(1).plusHours(1).plusMinutes(30));
        calendarExportResponse.setTags(new ArrayList<>(Arrays.asList("sport", "food")));
        return calendarExportResponse;
    }
}



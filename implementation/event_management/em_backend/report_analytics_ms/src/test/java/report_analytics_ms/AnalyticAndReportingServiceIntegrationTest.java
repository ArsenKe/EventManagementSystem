package report_analytics_ms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import report_analytics_ms.entities.Event;
import report_analytics_ms.model.CommonResponseModel;
import report_analytics_ms.model.ReportType;
import report_analytics_ms.repository.EventRepository;
import report_analytics_ms.repository.UserEventRepository;
import report_analytics_ms.repository.UserRepository;
import report_analytics_ms.service.AnalyticAndReportingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
public class AnalyticAndReportingServiceIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private UserRepository userRepository;

    private AnalyticAndReportingService analyticAndReportingService;

    @BeforeEach
    void setUp() {
        analyticAndReportingService = new AnalyticAndReportingService(eventRepository, userEventRepository, userRepository);
    }

    @Test
    void testGetEventReport_Success() {
        // Create test data
        Event event = new Event();
        event.setEventName("Event Name");
        event.setStartDateTime(LocalDateTime.now());
        event.setEndDateTime(LocalDateTime.now().plusHours(2));
        entityManager.persist(event);
        entityManager.flush();

        // Perform the test
        CommonResponseModel response = analyticAndReportingService.getEventReport(event.getId(), Pageable.unpaged());

        // Assertions
        assertNotNull(response);
        assertEquals(event.getId(), response.getEventId());
        assertEquals("Event Name", response.getEventName());
        assertEquals(ReportType.FEEDBACK.toString(), response.getReportName());
        assertEquals(ReportType.FEEDBACK, response.getReportType());
        assertNotNull(response.getRegistrationDate());
        assertNotNull(response.getEventEndDate());
        assertNotNull(response.getFeedbackList());
        assertTrue(response.getFeedbackList().isEmpty());
    }

    @Test
    void testGetEventReport_EventNotFound() {
        String nonExistingEventId = "nonExistingEventId";

        // Perform the test and catch the exception
        Exception exception = assertThrows(Exception.class, () ->
                analyticAndReportingService.getEventReport(nonExistingEventId, Pageable.unpaged()));
        assertTrue(exception.getMessage().contains("Event not found"));
    }

    @Test
    void testGetBookMarkReport_Success() {
        // Create test data
        List<Event> events = new ArrayList<>();
        Event event1 = new Event();
        event1.setId("event1");
        event1.setEventName("Event1");
        events.add(event1);

        Event event2 = new Event();
        event2.setId("event2");
        event2.setEventName("Event 2");
        events.add(event2);

        when(eventRepository.findAll(ArgumentMatchers.<Pageable>any())).thenReturn(new PageImpl<>(events));

        Page<CommonResponseModel> response = analyticAndReportingService.getBookMarkReport(ReportType.BOOKMARK, Pageable.unpaged());

        assertNotNull(response);
        assertEquals(2, response.getTotalElements());
        assertEquals("event1", response.getContent().get(0).getEventId());
        assertEquals("Event 1", response.getContent().get(0).getEventName());
        assertEquals("event2", response.getContent().get(1).getEventId());
        assertEquals("Event 2", response.getContent().get(1).getEventName());
        assertEquals(ReportType.FEEDBACK.toString(), response.getContent().get(0).getReportName());
        assertEquals(ReportType.FEEDBACK, response.getContent().get(0).getReportType());
        assertNotNull(response.getContent().get(0).getRegistrationDate());
        assertNotNull(response.getContent().get(0).getEventEndDate());
        assertNotNull(response.getContent().get(1).getEventId());
        assertNotNull(response.getContent().get(1).getEventName());
        assertEquals(ReportType.FEEDBACK.toString(), response.getContent().get(1).getReportName());
        assertEquals(ReportType.FEEDBACK, response.getContent().get(1).getReportType());
        assertNotNull(response.getContent().get(1).getRegistrationDate());
        assertNotNull(response.getContent().get(1).getEventEndDate());
    }



    @Test
    void testGetAttendanceReport_Success() {
        List<Event> events = new ArrayList<>();
        Event event1 = new Event();
        event1.setId("event1");
        event1.setEventName("Event 1");
        events.add(event1);

        Event event2 = new Event();
        event2.setId("event2");
        event2.setEventName("Event 2");
        events.add(event2);

        when(eventRepository.findAll(ArgumentMatchers.<Pageable>any())).thenReturn(new PageImpl<>(events));

        Page<CommonResponseModel> response = analyticAndReportingService.getAttendanceReport(ReportType.ATTENDANCE, Pageable.unpaged());

        assertNotNull(response);
        assertEquals(2, response.getTotalElements());
        assertEquals("event1", response.getContent().get(0).getEventId());
        assertEquals("Event 1", response.getContent().get(0).getEventName());
        assertEquals("event2", response.getContent().get(1).getEventId());
        assertEquals("Event 2", response.getContent().get(1).getEventName());
        assertEquals(ReportType.FEEDBACK.toString(), response.getContent().get(0).getReportName());
        assertEquals(ReportType.FEEDBACK, response.getContent().get(0).getReportType());
        assertNotNull(response.getContent().get(0).getRegistrationDate());
        assertNotNull(response.getContent().get(0).getEventEndDate());
        assertNotNull(response.getContent().get(1).getEventId());
        assertNotNull(response.getContent().get(1).getEventName());
        assertEquals(ReportType.FEEDBACK.toString(), response.getContent().get(1).getReportName());
        assertEquals(ReportType.FEEDBACK, response.getContent().get(1).getReportType());
        assertNotNull(response.getContent().get(1).getRegistrationDate());
        assertNotNull(response.getContent().get(1).getEventEndDate());
    }

    @Test
    void testGetOverallReport_Success() {
        List<Event> events = new ArrayList<>();
        Event event1 = new Event();
        event1.setId("event1");
        event1.setEventName("Event 1");
        events.add(event1);

        Event event2 = new Event();
        event2.setId("event2");
        event2.setEventName("Event 2");
        events.add(event2);

        when(eventRepository.findAll(ArgumentMatchers.<Pageable>any())).thenReturn(new PageImpl<>(events));

        Page<CommonResponseModel> response = analyticAndReportingService.getOverallReport(ReportType.OVERALL, Pageable.unpaged());
        assertNotNull(response);
        assertEquals(2, response.getTotalElements());
        assertEquals("event1", response.getContent().get(0).getEventId());
        assertEquals("Event 1", response.getContent().get(0).getEventName());
        assertEquals("event2", response.getContent().get(1).getEventId());
        assertEquals("Event 2", response.getContent().get(1).getEventName());
        assertEquals(ReportType.FEEDBACK.toString(), response.getContent().get(0).getReportName());
        assertEquals(ReportType.FEEDBACK, response.getContent().get(0).getReportType());
        assertNotNull(response.getContent().get(0).getRegistrationDate());
        assertNotNull(response.getContent().get(0).getEventEndDate());
        assertNotNull(response.getContent().get(1).getEventId());
        assertNotNull(response.getContent().get(1).getEventName());
        assertEquals(ReportType.FEEDBACK.toString(), response.getContent().get(1).getReportName());
        assertEquals(ReportType.FEEDBACK, response.getContent().get(1).getReportType());
        assertNotNull(response.getContent().get(1).getRegistrationDate());
        assertNotNull(response.getContent().get(1).getEventEndDate());
    }


}

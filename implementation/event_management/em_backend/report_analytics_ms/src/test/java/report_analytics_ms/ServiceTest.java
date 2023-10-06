package report_analytics_ms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import report_analytics_ms.model.ReportType;
import report_analytics_ms.repository.EventRepository;
import report_analytics_ms.service.AnalyticAndReportingService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private AnalyticAndReportingService analyticAndReportingService;

    @Test
    void getEventReport() {
        when(eventRepository.findById("1")).thenReturn(Optional.empty());
        assertThatThrownBy(()-> analyticAndReportingService.getEventReport("1"  ));
    }

    @Test
    void getBookMarkReport(){
        PageRequest page = PageRequest.of(0, 10);
        when(eventRepository.findAll(page)).thenReturn(Page.empty());
        analyticAndReportingService.getBookMarkReport(page);
        verify(eventRepository).findAll(page);
    }

    @Test
    void getAttendanceReport(){
        PageRequest page = PageRequest.of(0, 10);
        when(eventRepository.findAll(page)).thenReturn(Page.empty());
        analyticAndReportingService.getAttendanceReport(page);
        verify(eventRepository).findAll(page);
    }

    @Test
    void getOverallReport(){
        PageRequest page = PageRequest.of(0, 10);
        when(eventRepository.findAll(page)).thenReturn(Page.empty());
        analyticAndReportingService.getOverallReport(page);
        verify(eventRepository).findAll(page);
    }
}

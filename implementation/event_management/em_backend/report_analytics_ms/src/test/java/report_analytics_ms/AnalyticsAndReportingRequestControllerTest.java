package report_analytics_ms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.data.domain.PageRequest;
import report_analytics_ms.controller.AnalyticsAndReportingRequestController;
import report_analytics_ms.model.ReportType;
import report_analytics_ms.service.AnalyticAndReportingService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static reactor.core.publisher.Mono.never;


@ExtendWith(MockitoExtension.class)
public class AnalyticsAndReportingRequestControllerTest {

    @Mock
    private AnalyticAndReportingService analyticAndReportingService;

    @InjectMocks
    private AnalyticsAndReportingRequestController analyticsAndReportingRequestController;

    @Test
    void generateFeedBackReportTest() {
        when(analyticAndReportingService.getEventReport("1")).thenReturn(null);
        analyticsAndReportingRequestController.generateFeedBackReport("1", false, PageRequest.of(1,10));
        verify(analyticAndReportingService).getEventReport("1");
    }

    @Test
    void getEventGeneralBookMarkReport(){
        when(analyticAndReportingService.getBookMarkReport(PageRequest.of(1,10))).thenReturn(null);
        analyticsAndReportingRequestController.getEventGeneralReport(ReportType.BOOKMARK, PageRequest.of(1,10), false);
        verify(analyticAndReportingService).getBookMarkReport(PageRequest.of(1,10));
    }

    @Test
    void getEventGeneralAttendanceReport(){
        when(analyticAndReportingService.getAttendanceReport(PageRequest.of(1,10))).thenReturn(null);
        analyticsAndReportingRequestController.getEventGeneralReport(ReportType.ATTENDANCE, PageRequest.of(1,10), false);
        verify(analyticAndReportingService).getAttendanceReport(PageRequest.of(1,10));
    }

    @Test
    void getEventGeneralOverallReport(){
        when(analyticAndReportingService.getOverallReport(PageRequest.of(1,10))).thenReturn(null);
        analyticsAndReportingRequestController.getEventGeneralReport(ReportType.OVERALL, PageRequest.of(1,10), false);
        verify(analyticAndReportingService).getOverallReport(PageRequest.of(1,10));
    }

}

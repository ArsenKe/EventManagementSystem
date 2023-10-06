package report_analytics_ms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import report_analytics_ms.model.CommonResponseModel;
import report_analytics_ms.model.ReportType;
import report_analytics_ms.service.AnalyticAndReportingService;

import java.io.ByteArrayInputStream;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class AnalyticsAndReportingRequestController {
    private final AnalyticAndReportingService analyticAndReportingService;

    @GetMapping(value = "/events/{eventId}/reports")
    public ResponseEntity<CommonResponseModel> generateFeedBackReport(@PathVariable String eventId, @RequestParam(defaultValue = "false", required = false) boolean downloadable, @PageableDefault Pageable pageable){
        return downloadable ? getJsonFile(analyticAndReportingService.getEventReport(eventId)) : ResponseEntity.ok(analyticAndReportingService.getEventReport(eventId));
    }

    @GetMapping(value = "/events/reports")
    public ResponseEntity getEventGeneralReport(@RequestParam ReportType reportType, @PageableDefault Pageable pageable, @RequestParam(defaultValue = "false", required = false) boolean downloadable){
        Page<CommonResponseModel> repostModel = null;
        if(reportType.equals(ReportType.BOOKMARK)){
            repostModel = analyticAndReportingService.getBookMarkReport(pageable);
            return downloadable ? getJsonFile(repostModel) : ResponseEntity.ok(repostModel);
        }else if(reportType.equals(ReportType.ATTENDANCE)){
            repostModel = analyticAndReportingService.getAttendanceReport(pageable);
            return downloadable ? getJsonFile(repostModel) : ResponseEntity.ok(repostModel);
        }else if (reportType.equals(ReportType.OVERALL)){
            repostModel = analyticAndReportingService.getOverallReport(pageable);
            return downloadable ? getJsonFile(repostModel) : ResponseEntity.ok(repostModel);
        }else{
            repostModel = analyticAndReportingService.getFeedbackReport(pageable);
            return downloadable ? getJsonFile(repostModel) : ResponseEntity.ok(repostModel);
        }
    }

    private <T> ResponseEntity getJsonFile(T repostModel){
        ObjectMapper mapper = new ObjectMapper();

        try {
            byte[] bytes = mapper.writeValueAsBytes(repostModel instanceof Page ? ((Page<CommonResponseModel>)repostModel).getContent() : (CommonResponseModel)repostModel);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setContentDispositionFormData("attachment", "events.json");

            return ResponseEntity
                    .ok()
                    .contentLength(bytes.length)
                    .headers(headers)
                    .body(new InputStreamResource(new ByteArrayInputStream(bytes)));
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

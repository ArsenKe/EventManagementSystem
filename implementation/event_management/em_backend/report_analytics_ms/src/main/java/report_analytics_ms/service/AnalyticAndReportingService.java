package report_analytics_ms.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import report_analytics_ms.entities.Event;
import report_analytics_ms.exceptions.EventNotfoundException;
import report_analytics_ms.model.CommonResponseModel;
import report_analytics_ms.model.FeedbackModel;
import report_analytics_ms.model.ReportType;
import report_analytics_ms.repository.EventRepository;
import report_analytics_ms.repository.UserEventRepository;
import report_analytics_ms.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AnalyticAndReportingService {

    private final EventRepository eventRepository;
    private final UserEventRepository userEventRepository;
    private final UserRepository userRepository;

    //Generates a report for a specific event
    public CommonResponseModel getEventReport(String eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if(eventOptional.isEmpty()){
            throw EventNotfoundException.getEventNotFoundException();
        }
        Event event = eventOptional.get();
        return CommonResponseModel.builder()
                .eventId(event.getId())
                .eventName(event.getEventName())
                .reportName(ReportType.FEEDBACK.toString())
                .reportType(ReportType.FEEDBACK)
                .eventEndDate(event.getEndDateTime())
                .registrationDate(event.getStartDateTime())
                .feedbackList(new ArrayList<>(userEventRepository.findAllByEvent(event).stream().filter(userEvent -> userEvent.getFeedback() != null).map(userEvent -> FeedbackModel.builder()
                        .userId(userEvent
                                .getUser().getId())
                        .userName(userEvent
                                .getUser().getUserName())
                        .feedback(userEvent.getFeedback())
                        .build()).collect(Collectors.toList())))
                .build();
    }

    //Generates a report for all events that have been bookmarked
    public Page<CommonResponseModel> getBookMarkReport(Pageable pageable) {
        return eventRepository.findAll(pageable).map(event ->
                CommonResponseModel.builder()
                        .eventId(event.getId())
                        .eventName(event.getEventName())
                        .reportName(ReportType.BOOKMARK.toString())
                        .reportType(ReportType.BOOKMARK)
                        .eventEndDate(event.getEndDateTime())
                        .registrationDate(event.getStartDateTime())
                        .bookMarkedCount(userEventRepository.countByEventAndMarked(event,true))
                        .build()
        );
    }
    //Generates a report for all events that have had at least one person attend
    public Page<CommonResponseModel> getAttendanceReport( Pageable pageable) {
        return eventRepository.findAll(pageable).map(event ->
                CommonResponseModel.builder()
                        .eventId(event.getId())
                        .eventName(event.getEventName())
                        .reportName(ReportType.ATTENDANCE.toString())
                        .reportType(ReportType.ATTENDANCE)
                        .eventEndDate(event.getEndDateTime())
                        .registrationDate(event.getStartDateTime())
                        .bookMarkedCount(userEventRepository.countByEventAndAttendance(event,true))
                        .build()
        );
    }
    //Generates a report that includes data about all events
    public Page<CommonResponseModel> getOverallReport(Pageable pageable) {
        return eventRepository.findAll(pageable).map(event ->
                CommonResponseModel.builder()
                        .eventId(event.getId())
                        .eventName(event.getEventName())
                        .reportName(ReportType.OVERALL.toString())
                        .reportType(ReportType.OVERALL)
                        .eventEndDate(event.getEndDateTime())
                        .registrationDate(event.getStartDateTime())
                        .bookMarkedCount(userEventRepository.countByEventAndAttendance(event,true))
                        .bookMarkedCount(userEventRepository.countByEventAndMarked(event, true))
                        .build()
        );
    }
    // Generates a report that includes data about all feedback for all events
    public Page<CommonResponseModel> getFeedbackReport(Pageable pageable) {
        return eventRepository.findAll(pageable).map(event ->
                CommonResponseModel.builder()
                        .eventId(event.getId())
                        .eventName(event.getEventName())
                        .reportName(ReportType.FEEDBACK.toString())
                        .reportType(ReportType.FEEDBACK)
                        .eventEndDate(event.getEndDateTime())
                        .registrationDate(event.getStartDateTime())
                        .feedbackList(new ArrayList<>(userEventRepository.findAllByEvent(event).stream().filter(userEvent -> userEvent.getFeedback() != null).map(userEvent -> FeedbackModel.builder()
                                .userId(userEvent
                                        .getUser().getId())
                                .userName(userEvent
                                        .getUser().getUserName())
                                .feedback(userEvent.getFeedback())
                                .build()).collect(Collectors.toList())))
                        .bookMarkedCount(userEventRepository.countByEventAndAttendance(event,true))
                        .bookMarkedCount(userEventRepository.countByEventAndMarked(event, true))
                        .build()
        );
    }
}


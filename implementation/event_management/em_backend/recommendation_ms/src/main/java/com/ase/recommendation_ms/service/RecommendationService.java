package com.ase.recommendation_ms.service;


import com.ase.recommendation_ms.entity.*;
import com.ase.recommendation_ms.repository.BookmarkRepository;
import com.ase.recommendation_ms.repository.FeedbackRepository;
import com.ase.recommendation_ms.repository.RecommendationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@AllArgsConstructor

@Service
public class RecommendationService {

    @Autowired
    private final RecommendationRepository recommendationRepository;
    @Autowired
    private final FeedbackRepository feedbackRepository;
    @Autowired
    private final BookmarkRepository bookmarkRepository;

    public CustomReply getRecommendationsForUser(Long userId) {
        // GET ALL THE EVENTS ID FROM THE REPOSITORY
        // GET ALL THE ATTENDEES BOOKMARKED EVENTS
        // GET ALL THE ATTENDEES TAGS
        // GET ALL THE EVENTS WITH THE TAGS
        // GET ALL THE FEEDBACKS FOR THE EVENTS
        // GET ALL THE EVENTS WITH THE FEEDBACKS
        // RECOMMEND THE EVENTS WITH THE HIGHEST AVERAGE FEEDBACK SCORE
        // THAT MATCHES THE BOOKMARKED EVENTS TAGS AND THE ATTENDEES TAGS

        List<Event> events = recommendationRepository.getAllEvents();
        List<Feedback> feedbacks = feedbackRepository.getAllFeedbacks();

        // create a new list of events that has the same event id in the feedbacks list
        // and the event list

        List <Event> recommendedEvents = events.stream()
                .filter(event -> feedbacks.stream()
                        .anyMatch(feedback -> Objects.equals(feedback.getEventId(), event.getId())))
                .collect(Collectors.toList());

        return new CustomReply(events, feedbacks);
    }


    public List<Event> getRecommendedEvents(Long userId){
        List<Event> AllEvents = recommendationRepository.getAllEvents();
//        List<Feedback> AllFeedbacks = feedbackRepository.getAllFeedbacks();
//        List<Bookmark> userBookmarks = bookmarkRepository.findByUserId(userId);
//        // find a list of events that has the same tags as the user's bookmarked events
//        List<Event> EventsMatchingTheBookMarkedTags = AllEvents.stream()
//                .filter(event -> userBookmarks.stream()
//                        .anyMatch(bookmark -> Objects.equals(bookmark.getEvent().getId(), event.getId())))
//                .collect(Collectors.toList());
//
//        // organize the list of events by the average feedback score
//        List<Event> EventsSortedByAverageFeedbackScore = EventsMatchingTheBookMarkedTags.stream()
//                .sorted((event1, event2) -> {
//                    double event1AverageFeedbackScore = AllFeedbacks.stream()
//                            .filter(feedback -> Objects.equals(feedback.getEventId(), event1.getId()))
//                            .mapToDouble(Feedback::getRate)
//                            .average()
//                            .orElse(0.0);
//                    double event2AverageFeedbackScore = AllFeedbacks.stream()
//                            .filter(feedback -> Objects.equals(feedback.getEventId(), event2.getId()))
//                            .mapToDouble(Feedback::getRate)
//                            .average()
//                            .orElse(0.0);
//                    return Double.compare(event2AverageFeedbackScore, event1AverageFeedbackScore);
//                })
//                .collect(Collectors.toList());

        // return the first 5 events
//        return EventsSortedByAverageFeedbackScore.stream()
//                .limit(5)
//                .collect(Collectors.toList());

        return AllEvents;

    }
}


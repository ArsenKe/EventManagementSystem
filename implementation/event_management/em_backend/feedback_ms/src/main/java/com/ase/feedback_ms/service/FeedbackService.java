package com.ase.feedback_ms.service;

import com.ase.feedback_ms.entity.Feedback;
import com.ase.feedback_ms.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;



    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findByFeedbackId(id);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getFeedbacksByEventId(Long eventId) {
        return feedbackRepository.findByEventId(eventId);
    }

    public String addFeedback(Feedback feedback) {
            feedbackRepository.save(feedback);
        return "Feedback added successfully";
    }

    public List<Feedback> getFeedbacksByEventIds(List<Long> eventIds) {
        return feedbackRepository.findByEventIds(eventIds);
    }


}

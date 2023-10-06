package com.ase.feedback_ms.controller;

import com.ase.feedback_ms.entity.Feedback;
import com.ase.feedback_ms.repository.FeedbackRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }


    @RequestMapping(path = "/feedbacks/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Feedback>> getFeedbacksForEvent(@PathVariable("id") Long eventId) {
        try {
            List<Feedback> feedbacks = feedbackRepository.findByEventId(eventId);
            return ResponseEntity.ok(feedbacks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/feedbacks", method = RequestMethod.GET)
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        try {
            List<Feedback> feedbacks = feedbackRepository.findAll();
            return ResponseEntity.ok(feedbacks);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/feedbacks/add")
    public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback) {
        try {
            feedbackRepository.save(feedback);
            return ResponseEntity.ok(feedback);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

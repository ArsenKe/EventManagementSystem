package com.ase.feedback_ms.controller;

import com.ase.feedback_ms.entity.Feedback;
import com.ase.feedback_ms.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@WebMvcTest(FeedbackController.class)
class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeedbackService feedbackService;





    @Test
    void testGetFeedbacksByEventId() {

        Feedback feedback1 = new Feedback(452L,4L,"event was great !!",1,1,1);
        Feedback feedback2 = new Feedback(453L,5L,"event was great !!",3,5,3);


        List<Feedback> feedbacks = Stream.of(feedback1,feedback2).toList();
        when(feedbackService.getFeedbacksByEventId(4L)).thenReturn(feedbacks);

        assertEquals(feedbacks,feedbackService.getFeedbacksByEventId(4L));

    }

        @Test
    void testGetAllFeedbacks() {

        Feedback feedback1 = new Feedback(452L,4L,"event was great !!",1,1,1);
        Feedback feedback2 = new Feedback(453L,4L,"event was great !!",1,1,1);
        Feedback feedback3 = new Feedback(454L,4L,"event was great !!",1,1,1);
        Feedback feedback4 = new Feedback(455L,4L,"event was great !!",1,1,1);

        List<Feedback> feedbacks = Stream.of(feedback1,feedback2,feedback3,feedback4).toList();
        when(feedbackService.getAllFeedbacks()).thenReturn(feedbacks);

        assertEquals(feedbacks,feedbackService.getAllFeedbacks());

        }

    @Test
    void testCreateFeedback() {

        Feedback feedback1 = new Feedback(452L,4L,"event was great !!",1,1,1);

        String response = "Feedback added successfully";

        when(feedbackService.addFeedback(feedback1)).thenReturn(response);

        assertEquals(response,feedbackService.addFeedback(feedback1));

    }


    @Test
    void deleteFeedback() {

        Long feedbackId = 452L;

        // Call the deleteFeedback method
        feedbackService.deleteFeedback(feedbackId);

        // Verifying that deleteById method was called with the correct feedbackId
        verify(feedbackService).deleteFeedback(feedbackId);

    }


}
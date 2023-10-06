package com.ase.feedback_ms.Consumer;


import com.ase.feedback_ms.config.MessagingConfig;
import com.ase.feedback_ms.entity.Feedback;
import com.ase.feedback_ms.service.FeedbackService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class Consumer {

    @Autowired
    private FeedbackService feedbackService;

    @RabbitListener(queues = MessagingConfig.feedbackQueue)
    public String getFeedbacksByEventIds(String eventIds) {
        System.out.println("Message recieved from queue : " + eventIds);

        // parse the string userids to list of longs
        Type collectionType = new TypeToken<List<Long>>() {
        }.getType();        //response type
        List<Long> eventIdsList = new Gson().fromJson(eventIds, collectionType);

        List<Feedback> feedbacks = feedbackService.getFeedbacksByEventIds(eventIdsList);

        Gson gson = new Gson();
        return gson.toJson(feedbacks);


    }
}

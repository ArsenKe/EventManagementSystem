package com.ase.event_inventatory_ms.consumer;

import com.ase.event_inventatory_ms.config.MessagingConfig;
import com.ase.event_inventatory_ms.entity.Event;
import com.ase.event_inventatory_ms.entity.Location;
import com.ase.event_inventatory_ms.entity.Tag;
import com.ase.event_inventatory_ms.service.EventService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@Component
public class Consumer {

    @Autowired
    private EventService eventService;

    // for search ms-------------------
    @RabbitListener(queues = MessagingConfig.searchQueryQueue)
    public String getQuery(String query) throws IOException, TimeoutException, InterruptedException {
//        System.out.println("******************************From search listener******************************");
//        System.out.println(query);
        List<Event> events = eventService.searchEvents(query);

        Gson gson = new Gson();
        return gson.toJson(events);
    }

    // for recommendation/ bookmarks/ attendance ms-------------------
    @RabbitListener(queues = MessagingConfig.eventsByIdsQueue)
    public String getEventIds(String eventIds) throws IOException, TimeoutException, InterruptedException {

        // parse the string eventIds to list of longs
        Type collectionType = new TypeToken<List<Long>>() {
        }.getType();        //response type
        List<Long> eventIdsList = new Gson().fromJson(eventIds, collectionType);

        List<Event> events = eventService.findAllByEventIds(eventIdsList);

        Gson gson = new Gson();
        return gson.toJson(events);
    }


    // for bookmarks(tag update, add, delete)/ attendance ms-------------------
    @RabbitListener(queues = MessagingConfig.updateEventQueue)
    public String updateEvent(Event event) throws IOException, TimeoutException, InterruptedException {
        System.out.println("****************************From bookmark listener****************************");
        System.out.println(event);
        Event updatedEvent = eventService.updateEvent(event);

        Gson gson = new Gson();
        return gson.toJson(updatedEvent);
    }

    // for recommendation ms-------------------
    @RabbitListener(queues = MessagingConfig.eventsByTagsIdQueue)
    public String getEventsByTagId(String tagIds) throws IOException, TimeoutException, InterruptedException {
        System.out.println("****************************From recommendation listener****************************");
        // convert the string tagIds to list of longs
        Type collectionType = new TypeToken<List<Long>>() {
        }.getType();        //response type
        List<Long> eventTags = new Gson().fromJson(tagIds, collectionType);
        List<Event> allEvents = eventService.getEvents();
        // filter the events that has at least one tag in the list of tagId
        List<Event> filteredEvents = allEvents.stream()
                .filter(event -> event.getTags().stream()
                        .anyMatch(tag -> eventTags.contains(tag.getId())))
                .toList();


        Gson gson = new Gson();
        return gson.toJson(filteredEvents);
    }
}
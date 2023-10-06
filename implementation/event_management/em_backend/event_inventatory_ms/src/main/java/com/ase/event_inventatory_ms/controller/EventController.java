package com.ase.event_inventatory_ms.controller;

import com.ase.event_inventatory_ms.entity.CustomListResponse;
import com.ase.event_inventatory_ms.entity.CustomSingleResponse;
import com.ase.event_inventatory_ms.entity.Event;
import com.ase.event_inventatory_ms.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(path = "/events/add", method = RequestMethod.POST)
    public ResponseEntity<CustomSingleResponse> addEvent(@RequestBody Event event) {
//        reques example:++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        {
//            "eventName" :"demo 3",
//                "description":"demo description fsdfsdf sdf sdf",
//                "startDatetime":"2022-05-30T14:30:00",
//                "endDatetime":"2022-05-30T14:30:00",
//                "location":{
//            "id": 1
//        },
//            "capacity":123,
//                "tags": [
//            {"id":1},
//            {"id":2}
//                  ]
//        }
//        reques example:++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//        try {
            System.out.println(event);
            eventService.addEvent(event);
            return ResponseEntity.ok(new CustomSingleResponse("Event added successfully!"));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public ResponseEntity<CustomListResponse> getEvents() {

        try {
            List<Event> events = eventService.getEvents();
            return ResponseEntity.ok(new CustomListResponse("Event list!", events));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomListResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomSingleResponse> eventDetails(@PathVariable("id") Long id) {

        try {
            Optional<Event> event = eventService.eventDetails(id);
            return ResponseEntity.ok(new CustomSingleResponse("Event details!", event));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/events/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CustomSingleResponse> deleteEvent(@PathVariable("id") Long id) {

        try {
            eventService.deleteEvent(id);
            return ResponseEntity.ok(new CustomSingleResponse("Event deleted!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/events/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CustomSingleResponse> updateEvent(@RequestBody  Event event, @PathVariable("id") Long id) {

        try {
            event.setId(id);
            eventService.updateEvent(event);
            return ResponseEntity.ok(new CustomSingleResponse("Event updated!"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomSingleResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

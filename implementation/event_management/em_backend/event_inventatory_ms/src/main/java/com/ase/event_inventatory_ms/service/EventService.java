package com.ase.event_inventatory_ms.service;

import com.ase.event_inventatory_ms.entity.Event;
import com.ase.event_inventatory_ms.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public Optional<Event> eventDetails(Long id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public List<Event> searchEvents(String query) {
//        System.out.println(query);
        return eventRepository.searchEvents(query);
    }
    public List<Event> findAllByEventIds(List<Long> ids) {
//        System.out.println(query);
        return eventRepository.findAllByIds(ids);
    }

}
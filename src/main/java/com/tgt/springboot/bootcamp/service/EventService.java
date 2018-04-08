package com.tgt.springboot.bootcamp.service;

import com.tgt.springboot.bootcamp.dto.EventDto;
import com.tgt.springboot.bootcamp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by z013hmy on 4/3/18.
 */
@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventDto saveEvent(EventDto newEvent) {
        return eventRepository.save(newEvent);
    }

    public EventDto getEvent(Integer eventId) {
        return eventRepository.load(eventId);
    }

    public List<EventDto> getAllEvents() {
        return eventRepository.getAll();
    }
}

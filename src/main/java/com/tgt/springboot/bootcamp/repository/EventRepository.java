package com.tgt.springboot.bootcamp.repository;

import com.tgt.springboot.bootcamp.dto.EventDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by z013hmy on 4/3/18.
 */
@Repository
public class EventRepository {

    private Map<Integer, EventDto> eventData = new HashMap<>();

    public EventDto save(EventDto newEvent) {
        if (newEvent.getEventId() == null) {
            newEvent.setEventId(new Random().nextInt());
        }
        eventData.put(newEvent.getEventId(), newEvent);
        return newEvent;
    }

    public EventDto load(Integer eventId) {
        return eventData.get(eventId);
    }

    public List<EventDto> getAll() {
        return new ArrayList<>(eventData.values());
    }
}

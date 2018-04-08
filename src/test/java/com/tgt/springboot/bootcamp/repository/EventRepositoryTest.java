package com.tgt.springboot.bootcamp.repository;

import com.tgt.springboot.bootcamp.dto.EventDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created on 4/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EventRepository.class})
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void getAllTest() {
        assertNotNull(eventRepository.getAll());
    }

    @Test
    public void saveNoIdTest() {
        EventDto event = new EventDto();
        event.setEventMessage("event message");

        EventDto output = eventRepository.save(event);
        assertNotNull(output.getEventId());
        assertEquals("event message", output.getEventMessage());
    }

    @Test
    public void saveWithIdTest() {
        EventDto event = new EventDto();
        event.setEventMessage("event message");
        event.setEventId(22);

        EventDto output = eventRepository.save(event);
        assertEquals((Integer) 22, output.getEventId());
        assertEquals("event message", output.getEventMessage());
    }

    @Test
    public void saveAndLoadTest() {
        EventDto event = new EventDto();
        event.setEventMessage("event message");
        event.setEventId(22);

        eventRepository.save(event);
        EventDto output = eventRepository.load(22);
        assertEquals((Integer) 22, output.getEventId());
        assertEquals("event message", output.getEventMessage());
    }

}

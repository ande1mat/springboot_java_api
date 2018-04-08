package com.tgt.springboot.bootcamp.service;

import com.tgt.springboot.bootcamp.dto.EventDto;
import com.tgt.springboot.bootcamp.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by z013hmy on 4/3/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EventService.class})
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @MockBean
    private EventRepository eventRepositoryMock;

    @Test
    public void getEventTest() throws Exception {
        ArgumentCaptor<Integer> eventIdCaptor = ArgumentCaptor.forClass(Integer.class);

        when(eventRepositoryMock.load(Matchers.eq(1))).thenReturn(new EventDto());

        EventDto outputEvent = eventService.getEvent(1);
        assertEquals(new EventDto(), outputEvent);

        Mockito.verify(eventRepositoryMock, times(1)).load(eventIdCaptor.capture());
        assertEquals((Integer) 1, eventIdCaptor.getValue());
    }

    @Test(expected = RuntimeException.class)
    public void getEventExceptionTest() throws Exception {
        when(eventRepositoryMock.load(Matchers.eq(1))).thenThrow(new RuntimeException());

        eventService.getEvent(1);
    }

    @Test
    public void saveEventTest() throws Exception {
        Mockito.when(eventRepositoryMock.save(Matchers.any())).then(returnsFirstArg());

        EventDto outputEvent = eventService.saveEvent(new EventDto());
        assertEquals(new EventDto(), outputEvent);
    }

    @Test(expected = RuntimeException.class)
    public void saveEventExceptionTest() throws Exception {
        Mockito.when(eventRepositoryMock.save(Matchers.any())).thenThrow(new RuntimeException());

        eventService.saveEvent(new EventDto());
    }
}

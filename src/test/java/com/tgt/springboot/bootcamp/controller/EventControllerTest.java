package com.tgt.springboot.bootcamp.controller;

import com.tgt.springboot.bootcamp.dto.EventDto;
import com.tgt.springboot.bootcamp.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by z013hmy on 4/3/18.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {EventController.class})
@WebMvcTest(EventController.class)
public class EventControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private EventService eventServiceMock;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void pingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/events/ping"))
               .andExpect(status().isOk())
               .andExpect(content().string("PING!"));
    }

    @Test
    public void getEventsTest() throws Exception {
        when(eventServiceMock.getAllEvents()).thenReturn(Arrays.asList(new EventDto()));
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/events/"))
               .andExpect(status().isOk())
               .andExpect(content().string("[{\"event_id\":null,\"event_message\":null}]"));
    }

    @Test(expected = Exception.class)
    public void getEventsExceptionTest() throws Exception {
        when(eventServiceMock.getAllEvents()).thenThrow(new RuntimeException("message"));
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/events/"))
               .andExpect(status().isOk())
               .andExpect(content().string("[]"));
    }

    @Test
    public void getEventTest() throws Exception {
        EventDto expectedEvent = new EventDto();
        expectedEvent.setEventId(123);
        expectedEvent.setEventMessage("123 event");
        when(eventServiceMock.getEvent(any())).thenReturn(expectedEvent);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/events/123"))
               .andExpect(status().isOk())
               .andExpect(content().string("{\"event_id\":123,\"event_message\":\"123 event\"}"));
    }

    @Test
    public void postEventTest() throws Exception {
        String jsonContent = "{\"event_message\": \"this is a new message\"}";
        when(eventServiceMock.saveEvent(any())).then(returnsFirstArg());
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/events/").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
                               .andExpect(status().isCreated())
                               .andReturn().getResponse().getContentAsString();
        assertTrue(result.contains("\"event_message\":\"this is a new message\""));
    }

    @Test
    public void postEmptyEventTest() throws Exception {
        String jsonContent = "{}";
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/events/").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
    }

    @Test
    public void postEventExceptionTest() throws Exception {
        String jsonContent = "{\"event_message\": \"this is a new message\"}";
        when(eventServiceMock.saveEvent(any())).thenThrow(new RuntimeException("message"));
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/events/").content(jsonContent).contentType(MediaType.APPLICATION_JSON))
                               .andExpect(status().isServiceUnavailable())
                               .andReturn().getResponse().getContentAsString();
        assertTrue(result.contains("{\n\"message\": \"There was an error accessing the service\"\n}"));
    }
}

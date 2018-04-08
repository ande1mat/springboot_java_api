package com.tgt.springboot.bootcamp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.springboot.bootcamp.dto.EventDto;
import com.tgt.springboot.bootcamp.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created on 4/3/18.
 */
@RestController
@RequestMapping(value = "/v1/events")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    private EventService eventService;
    private ObjectMapper mapper;

    @Autowired
    public EventController(EventService eventService, ObjectMapper mapper) {
        this.eventService = eventService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity getPing() {
        logger.info("get /v1/events/ping called");
        return new ResponseEntity<>("PING!", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getEvents() {
        logger.info("GET /v1/events called");
        return new ResponseEntity(eventService.getAllEvents(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public ResponseEntity getEvent(@PathVariable Integer eventId) {
        logger.info("GET /v1/events/{} called", eventId);
        printLog(eventId);
        return new ResponseEntity(eventService.getEvent(eventId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveEvent(@Valid @RequestBody EventDto event) {
        logger.info("POST /v1/events called");
        printLog(event);
        try {
            return new ResponseEntity<>(eventService.saveEvent(event), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("{\n\"message\": \"There was an error accessing the service\"\n}", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    private void printLog(Object object) {
        try {
            logger.info("Object Log: {}", mapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

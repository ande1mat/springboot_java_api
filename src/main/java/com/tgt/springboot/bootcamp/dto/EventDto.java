package com.tgt.springboot.bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

/**
 * Created on 4/3/18.
 */
public class EventDto {

    @JsonProperty("event_id")
    private Integer eventId;

    @NotEmpty
    @JsonProperty("event_message")
    private String eventMessage;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventDto eventDto = (EventDto) o;

        return eventDto.hashCode() == this.hashCode();

    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventMessage);
    }

    @Override
    public String toString() {
        return "EventDto{" +
               "eventId=" + eventId +
               ", eventMessage='" + eventMessage + '\'' +
               '}';
    }
}

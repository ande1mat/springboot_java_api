package com.tgt.springboot.bootcamp.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Created on 4/4/18.
 */
@Component
public class EventHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().withDetail("EventHealth", "EventHealth is ok!").build();
    }
}

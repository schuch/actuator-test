package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class SingleHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingleHealthIndicator.class);

    public SingleHealthIndicator() {
        LOGGER.info("created SingleHealthIndicator");
    }

    @Override
    public Health health() {
        return Health.up().build();
    }
}
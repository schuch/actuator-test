package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class TestHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestHealthIndicator.class);

    public TestHealthIndicator(TestBean testBean) {
        LOGGER.info("created TestHealthIndicator for {}", testBean.getName());
    }

    @Override
    public Health health() {
        return Health.up().build();
    }
}
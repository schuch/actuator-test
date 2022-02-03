package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public TestBean createTestBean1() {
        return new TestBean("1");
    }

    @Bean
    public TestBean createTestBean2() {
        return new TestBean("2");
    }
}

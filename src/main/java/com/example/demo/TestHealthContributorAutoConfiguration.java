package com.example.demo;

import org.springframework.boot.actuate.autoconfigure.health.CompositeHealthContributorConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(TestBean.class)
@ConditionalOnEnabledHealthIndicator("test")
public class TestHealthContributorAutoConfiguration extends CompositeHealthContributorConfiguration<TestHealthIndicator, TestBean> {

    @Bean
    public HealthContributor testHealthContributor(Map<String, TestBean> testBean) {
        return createContributor(testBean);
    }

}

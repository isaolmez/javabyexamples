package com.javabyexamples.spring.core.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class WeatherStation {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public WeatherStation(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void observe() {
        eventPublisher.publishEvent(new TemperatureEvent(this, RandomTemperature.getTemperature()));
    }
}

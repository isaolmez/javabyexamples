package com.javabyexamples.spring.core.eventlistener;

import org.springframework.context.ApplicationEvent;

public class TemperatureEvent extends ApplicationEvent {

    private final int temperature;

    public TemperatureEvent(Object source, int temperature) {
        super(source);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }
}

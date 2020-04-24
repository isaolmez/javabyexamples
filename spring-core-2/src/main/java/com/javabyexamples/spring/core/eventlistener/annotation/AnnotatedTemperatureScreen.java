package com.javabyexamples.spring.core.eventlistener.annotation;

import com.javabyexamples.spring.core.eventlistener.TemperatureEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedTemperatureScreen {

    @EventListener
    public void showTemperature(TemperatureEvent event) {
        System.out.println("Temperature is " + event.getTemperature());
    }
}

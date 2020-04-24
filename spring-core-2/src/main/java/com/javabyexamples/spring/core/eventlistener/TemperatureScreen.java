package com.javabyexamples.spring.core.eventlistener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TemperatureScreen implements ApplicationListener<TemperatureEvent> {

    @Override
    public void onApplicationEvent(TemperatureEvent event) {
        System.out.println("Temperature is " + event.getTemperature());
    }
}

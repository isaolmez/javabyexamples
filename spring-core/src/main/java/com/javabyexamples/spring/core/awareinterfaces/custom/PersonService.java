package com.javabyexamples.spring.core.awareinterfaces.custom;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PersonService implements ActiveProfilesAware {

    private List<String> activeProfiles;

    @PostConstruct
    public void init() {
        System.out.println("Active profiles: " + activeProfiles);
    }

    @Override
    public void setActiveProfiles(List<String> activeProfiles) {
        this.activeProfiles = activeProfiles;
    }
}

package com.javabyexamples.spring.core.awareinterfaces.custom;

import java.util.List;
import org.springframework.beans.factory.Aware;

public interface ActiveProfilesAware extends Aware {

    void setActiveProfiles(List<String> activeProfiles);
}

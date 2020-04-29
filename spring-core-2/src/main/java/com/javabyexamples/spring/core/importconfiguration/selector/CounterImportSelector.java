package com.javabyexamples.spring.core.importconfiguration.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class CounterImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        if (isOnLocal()) {
            return new String[]{DefaultCounter.class.getName()};
        }

        return new String[]{ThreadSafeCounter.class.getName()};
    }

    private boolean isOnLocal() {
        return false;
    }
}

package com.javabyexamples.spring.core.importannotation.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class CounterImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        if (isOnLocal()) {
            return new String[]{LocalCounterConfiguration.class.getName()};
        }

        return new String[]{ProdCounterConfiguration.class.getName()};
    }

    private boolean isOnLocal() {
        return false;
    }
}

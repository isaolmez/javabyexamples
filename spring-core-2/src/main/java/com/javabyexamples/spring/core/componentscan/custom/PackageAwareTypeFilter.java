package com.javabyexamples.spring.core.componentscan.custom;

import java.io.IOException;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class PackageAwareTypeFilter implements TypeFilter {

    @Override
    public final boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
      throws IOException {
        final ClassMetadata classMetadata = metadataReader.getClassMetadata();
        return classMetadata.getClassName().startsWith("com.javabyexamples.spring.core.componentscan.level1");
    }
}

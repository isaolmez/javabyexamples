package com.javabyexamples.spring.mvc1.restwithxml.castor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.castor.CastorMarshaller;

@Configuration
public class XmlMessageConverterConfiguration {

    @Bean
    public MarshallingHttpMessageConverter marshallingCastor() {
        return new MarshallingHttpMessageConverter(castorMarshaller());
    }

    @Bean
    public CastorMarshaller castorMarshaller() {
        CastorMarshaller castorMarshaller = new CastorMarshaller();
        castorMarshaller.setMappingLocation(new ClassPathResource("xml/castor/castor-mapping.xml"));
        return castorMarshaller;
    }
}

package com.javabyexamples.spring.mvc1.restwithxml.jaxb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlMessageConverterConfiguration {

    @Bean
    public MarshallingHttpMessageConverter marshallingJaxb() {
        return new MarshallingHttpMessageConverter(jaxb2Marshaller());
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("com.javabyexamples.spring.mvc1.restwithxml.jaxb");
        return jaxb2Marshaller;
    }
}

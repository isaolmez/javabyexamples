package com.javabyexamples.spring.mvc1.restwithxml.xstream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class XmlMessageConverterConfiguration {

    @Bean
    public MarshallingHttpMessageConverter marshallingXstream() {
        return new MarshallingHttpMessageConverter(xstreamMarshaller());
    }

    @Bean
    public XStreamMarshaller xstreamMarshaller() {
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        return xStreamMarshaller;
    }
}

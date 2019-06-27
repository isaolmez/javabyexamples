package com.javabyexamples.spring.mvc1.restwithxml.xstream;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class XmlMessageConverterConfiguration {

    @Bean
    public MarshallingHttpMessageConverter marshallingXstream() {
        return new MarshallingHttpMessageConverter(xstreamMarshallerWithoutAnnotations());
    }

    @Bean
    public XStreamMarshaller xstreamMarshaller() {
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAnnotatedClasses(new Class[]{PersonDetails.class, PersonCriteria.class});
        xStreamMarshaller.setAutodetectAnnotations(true);
        return xStreamMarshaller;
    }

    @Bean
    public XStreamMarshaller xstreamMarshallerWithoutAnnotations() {
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        Map<String, Object> aliases = new HashMap<>();
        aliases.put("criteria", PersonCriteria.class);
        aliases.put("details", PersonDetails.class);
        xStreamMarshaller.setAliases(aliases);
        return xStreamMarshaller;
    }
}

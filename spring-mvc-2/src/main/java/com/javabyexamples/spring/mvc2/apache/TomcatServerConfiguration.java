package com.javabyexamples.spring.mvc2.apache;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatServerConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Value("${ajp.port}")
    private int ajpPort;

    @Override
    public void customize(TomcatServletWebServerFactory server) {
        server.addAdditionalTomcatConnectors(redirectConnector());
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("AJP/1.3");
        connector.setScheme("http");
        connector.setPort(ajpPort);
        return connector;
    }
}

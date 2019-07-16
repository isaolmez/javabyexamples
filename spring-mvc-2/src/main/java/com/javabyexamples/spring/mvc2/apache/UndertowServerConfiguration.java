package com.javabyexamples.spring.mvc2.apache;

import io.undertow.Undertow.Builder;
import io.undertow.server.HttpHandler;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

@Component
public class UndertowServerConfiguration implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {

    @Value("${ajp.port}")
    private int ajpPort;

    @Override
    public void customize(UndertowServletWebServerFactory server) {
        server.addBuilderCustomizers(new AjpCustomizer());
    }

    private class AjpCustomizer implements UndertowBuilderCustomizer {

        @Override
        public void customize(Builder builder) {
            builder.addAjpListener(ajpPort, "0.0.0.0");
        }
    }
}

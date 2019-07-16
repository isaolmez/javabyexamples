package com.javabyexamples.spring.mvc2.serverconfiguration.tomcat.customizer;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ServerConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(new DefaultTomcatConnectorCustomizer());
        factory.addContextCustomizers(new DefaultTomcatContextCustomizer());
    }

    private static class DefaultTomcatConnectorCustomizer implements TomcatConnectorCustomizer {

        @Override
        public void customize(Connector connector) {
            connector.setPort(8081);
            connector.setAttribute("server", "MyAppServer");
        }
    }

    private static class DefaultTomcatContextCustomizer implements TomcatContextCustomizer {

        @Override
        public void customize(Context context) {
            context.setPath("/api");
        }
    }
}

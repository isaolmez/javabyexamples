package com.javabyexamples.java.test.wiremock.mapping;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.javabyexamples.java.test.wiremock.server.NoOpNotifier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MappingConfiguration {

    public static void main(String[] args) {
        final MappingConfiguration mappingConfiguration = new MappingConfiguration();
        final WireMockServer wireMockServer = mappingConfiguration.configureAndGet();
    }

    public WireMockServer configureAndGet() {
        final WireMockConfiguration configuration = new WireMockConfiguration()
          .port(8030)
          .disableRequestJournal()
          .notifier(new NoOpNotifier())
          .extensions(new RequestDataAwareResponseTransformer())
          .usingFilesUnderClasspath("server");
        final WireMockServer wireMockServer = new WireMockServer(configuration);
        wireMockServer.start();
        log.info("WireMock server started.");
        return wireMockServer;
    }
}

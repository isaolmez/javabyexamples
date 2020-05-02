package com.javabyexamples.java.test.wiremock.server;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerConfiguration {

    public static void main(String[] args) {
        final ServerConfiguration serverConfiguration = new ServerConfiguration();

//        serverConfiguration.basic();
        serverConfiguration.configure();
    }

    public void basic() {
        final WireMockConfiguration configuration = new WireMockConfiguration().port(8030);
        final WireMockServer wireMockServer = new WireMockServer(configuration);
        wireMockServer.start();
        log.info("WireMock server started.");

        wireMockServer.stop();
        log.info("WireMock server stopped.");
    }

    public void configure() {
        final WireMockConfiguration configuration = new WireMockConfiguration()
          .port(8030)
          .disableRequestJournal()
          .usingFilesUnderClasspath("server");
        final WireMockServer wireMockServer = new WireMockServer(configuration);
        wireMockServer.start();
        log.info("WireMock server started.");

        wireMockServer.stop();
        log.info("WireMock server stopped.");
    }

    public WireMockServer configureAndGet() {
        final WireMockConfiguration configuration = new WireMockConfiguration()
          .port(8030)
          .disableRequestJournal()
          .notifier(new NoOpNotifier())
          .usingFilesUnderClasspath("server");
        final WireMockServer wireMockServer = new WireMockServer(configuration);
        wireMockServer.start();
        log.info("WireMock server started.");
        return wireMockServer;
    }
}

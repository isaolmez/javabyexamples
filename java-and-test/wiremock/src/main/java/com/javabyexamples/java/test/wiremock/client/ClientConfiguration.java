package com.javabyexamples.java.test.wiremock.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.admin.model.ListStubMappingsResult;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.javabyexamples.java.test.wiremock.server.ServerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Slf4j
public class ClientConfiguration {

    public static void main(String[] args) {
        final ServerConfiguration serverConfiguration = new ServerConfiguration();
        final ClientConfiguration clientConfiguration = new ClientConfiguration();

        final WireMockServer wireMockServer = serverConfiguration.configureAndGet();
        clientConfiguration.registerMapping(wireMockServer);
        clientConfiguration.listMappings(wireMockServer);
    }

    public void listMappings(WireMockServer wireMockServer) {
        final WireMock wireMockClient = new WireMock(wireMockServer.getOptions().portNumber());
        final ListStubMappingsResult result = wireMockClient.allStubMappings();

        log.info("Stub mappings: {}", new ReflectionToStringBuilder(result));
    }

    public void registerMapping(WireMockServer wireMockServer) {
        final WireMock wireMockClient = new WireMock(wireMockServer.getOptions().portNumber());
        final StubMapping stubMapping = get(urlPathEqualTo("/hey"))
          .willReturn(aResponse()
            .withStatus(200)
            .withBody("Heyy"))
          .build();
        wireMockClient.register(stubMapping);
    }
}

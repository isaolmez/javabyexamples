package com.javabyexamples.apache.httpclient5.settinguseragent;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class UserAgentHttpClient {

    public void executeAndDefaultUserAgent() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                handleResponse(response);
            }
        }
    }

    public void executeAndSetUserAgent() throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("HttpClient Custom User Agent")
                .build()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                handleResponse(response);
            }
        }
    }

    public void executeAndDisableUserAgent() throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .disableDefaultUserAgent()
                .build()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                handleResponse(response);
            }
        }
    }

    public void executeAndSetUserAgentWithInterceptor() throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .addRequestInterceptorLast(new HttpRequestInterceptor() {
                    private final String[] userAgents = new String[]{"UserAgent1", "UserAgent2", "UserAgent3"};
                    private final Random random = new Random();

                    @Override
                    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) throws HttpException, IOException {
                        httpRequest.setHeader("User-Agent", userAgents[random.nextInt(3)]);
                    }
                })
                .build()) {

            final HttpGet httpGet = new HttpGet(GET_URL);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                handleResponse(response);
            }
        }
    }

    private void handleResponse(CloseableHttpResponse response) throws IOException, ParseException {
        String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        System.out.println("Response body: " + responseBody);
        EntityUtils.consumeQuietly(response.getEntity());
    }

    public static void main(String[] args) throws Exception {
        UserAgentHttpClient httpClient = new UserAgentHttpClient();
        httpClient.executeAndDefaultUserAgent();
        httpClient.executeAndSetUserAgent();
        httpClient.executeAndDisableUserAgent();
        httpClient.executeAndSetUserAgentWithInterceptor();
    }
}
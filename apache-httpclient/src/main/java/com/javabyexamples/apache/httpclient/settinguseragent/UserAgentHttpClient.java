package com.javabyexamples.apache.httpclient.settinguseragent;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

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
          .addInterceptorLast(new HttpRequestInterceptor() {
              private final String[] userAgents = new String[]{"UserAgent1", "UserAgent2", "UserAgent3"};
              private final Random random = new Random();

              @Override
              public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
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

    private void handleResponse(CloseableHttpResponse response) throws IOException {
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
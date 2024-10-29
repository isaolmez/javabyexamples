package com.javabyexamples.apache.httpclient5.retrying;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.util.TimeValue;

import java.io.IOException;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class RetryingHttpClient {

    public void executeRetryingThreeTimesImplicitly() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .addRequestInterceptorLast(new HttpRequestInterceptor() {
                    @Override
                    public void process(HttpRequest request, EntityDetails entityDetails, HttpContext context) throws IOException {
                        throw new IOException("Planned");
                    }
                })
                .build()) {

            executeGetRequest(httpClient);
        }
    }

    public void executeRetryingTenTimesExplicitly() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .addRequestInterceptorLast(new HttpRequestInterceptor() {
                    @Override
                    public void process(HttpRequest request, EntityDetails entityDetails, HttpContext context) throws IOException {
                        throw new IOException("Planned");
                    }
                })
                .build()) {

            executeGetRequest(httpClient);
        }
    }

    public void executeWithDisablingRetries() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .addRequestInterceptorLast(new HttpRequestInterceptor() {
                    @Override
                    public void process(HttpRequest request, EntityDetails entityDetails, HttpContext context) throws IOException {
                        throw new IOException("Planned");
                    }
                })
                .disableAutomaticRetries()
                .build()) {

            executeGetRequest(httpClient);
        }
    }


    public void executeRetryingWithCustom() throws Exception {
        HttpRequestRetryStrategy retryStrategy = new DefaultHttpRequestRetryStrategy(10, TimeValue.ofSeconds(1));
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .addRequestInterceptorLast(new HttpRequestInterceptor() {
                    @Override
                    public void process(HttpRequest request, EntityDetails entityDetails, HttpContext context) throws IOException {
                        throw new IOException("Planned");
                    }
                })
                .setRetryStrategy(retryStrategy)
                .build()) {

            executeGetRequest(httpClient);
        }
    }

    private void executeGetRequest(CloseableHttpClient httpClient) throws IOException {
        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            System.out.println(response.getCode());
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    public static void main(String[] args) throws Exception {
        RetryingHttpClient httpClient = new RetryingHttpClient();
        httpClient.executeRetryingThreeTimesImplicitly();
        httpClient.executeRetryingTenTimesExplicitly();
        httpClient.executeWithDisablingRetries();
        httpClient.executeRetryingWithCustom();
    }
}

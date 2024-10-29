package com.javabyexamples.apache.httpclient5.retrying;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpResponseInterceptor;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;

import static com.javabyexamples.apache.httpclient5.Constants.*;

public class ResponseStatusRetryingHttpClient {

    public void doesNotRetryFor400() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .build()) {

            executeRequestForStatus(httpClient, STATUS_400_URL);
        }
    }

    public void doesNotRetryFor404() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .build()) {

            executeRequestForStatus(httpClient, STATUS_404_URL);
        }
    }

    public void doesNotRetryFor500() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .build()) {

            executeRequestForStatus(httpClient, STATUS_500_URL);
        }
    }

    public void retriesFor500WithResponseInterceptor() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .addResponseInterceptorLast(new HttpResponseInterceptor() {
                    @Override
                    public void process(HttpResponse response, EntityDetails entityDetails, HttpContext context) throws IOException {
                        if (response.getCode() == 500) {
                            throw new IOException("Retry it");
                        }
                    }
                })
                .build()) {

            executeRequestForStatus(httpClient, STATUS_500_URL);
        }
    }

    private void executeRequestForStatus(CloseableHttpClient httpClient, String url) throws IOException {
        final HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            System.out.println(response.getCode());
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    public static void main(String[] args) throws Exception {
        ResponseStatusRetryingHttpClient httpClient = new ResponseStatusRetryingHttpClient();
        httpClient.doesNotRetryFor400();
        httpClient.doesNotRetryFor404();
        httpClient.doesNotRetryFor500();
        httpClient.retriesFor500WithResponseInterceptor();
    }
}

package com.javabyexamples.apache.httpclient5.connectionpooling;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class PoolingHttpClient {

    public void executeWithPooled() throws Exception {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                System.out.println(response.getCode());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public void executeWithDefaultHttpClient() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                System.out.println(response.getCode());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PoolingHttpClient httpClient = new PoolingHttpClient();
        httpClient.executeWithDefaultHttpClient();
        httpClient.executeWithPooled();
    }
}

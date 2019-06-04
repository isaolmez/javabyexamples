package com.javabyexamples.apache.httpclient.connectionpooling;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class PoolingHttpClient {

    public void pooledHttpClient() throws Exception {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(20);

        final CloseableHttpClient httpClient = HttpClients.custom()
          .setConnectionManager(connectionManager)
          .build();

        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    public void pooledHttpClientBuilder() throws Exception {
        final CloseableHttpClient httpClient = HttpClients.custom()
          .setMaxConnTotal(100)
          .setMaxConnPerRoute(20)
          .build();

        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    public void defaultHttpClient() throws Exception {
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    public void caveats() throws Exception {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultMaxPerRoute(1);

        RequestConfig requestConfig = RequestConfig.custom()
          .setConnectionRequestTimeout(2000)
          .build();

        final CloseableHttpClient httpClient = HttpClients.custom()
          .setDefaultRequestConfig(requestConfig)
          .setConnectionManager(connectionManager)
          .build();

        final HttpGet httpGet = new HttpGet(GET_URL);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    StatusLine statusLine = response.getStatusLine();
                    System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                    EntityUtils.consumeQuietly(response.getEntity());
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
    }


    public static void main(String[] args) throws Exception {
        PoolingHttpClient httpClient = new PoolingHttpClient();
//        httpClient.pooledHttpClient();
        httpClient.caveats();
    }
}

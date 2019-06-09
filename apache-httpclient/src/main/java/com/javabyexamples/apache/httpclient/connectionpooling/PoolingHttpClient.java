package com.javabyexamples.apache.httpclient.connectionpooling;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

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
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public void executeWithPooledUsingHttpClientBuilder() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                                                         .setMaxConnTotal(100)
                                                         .setMaxConnPerRoute(20)
                                                         .build()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public void executeWithDefaultHttpClient() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        PoolingHttpClient httpClient = new PoolingHttpClient();
        httpClient.executeWithDefaultHttpClient();
        httpClient.executeWithPooled();
        httpClient.executeWithPooledUsingHttpClientBuilder();
    }
}

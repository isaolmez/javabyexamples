package com.javabyexamples.apache.httpclient5.timeout;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.*;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class TimeoutAwareHttpClient {

    public void executeAndSetTimeoutWithRequestConfig() throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(1000))
                .build();
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setSocketTimeout(Timeout.ofMinutes(1))
                .setConnectTimeout(Timeout.ofMinutes(1))
                .setTimeToLive(TimeValue.ofMinutes(10))
                .build();
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultConnectionConfig(connectionConfig)
                .build();
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                StatusLine statusLine = new StatusLine(response);
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public void executeAndSetTimeoutWithRequestConfigPerRequest() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(Timeout.ofMilliseconds(1000))
                    .setConnectTimeout(Timeout.ofMilliseconds(1000))
                    .build();
            final HttpGet httpGet = new HttpGet(GET_URL);
            httpGet.setConfig(requestConfig);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                StatusLine statusLine = new StatusLine(response);
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public void executeAndSetTimeoutWithFutureTask() throws Exception {
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setMaxConnPerRoute(5)
                .setMaxConnTotal(5)
                .build();
        HttpClient httpclient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .build();
        ExecutorService execService = Executors.newFixedThreadPool(5);
        FutureRequestExecutionService requestExecutionService = new FutureRequestExecutionService(httpclient, execService);
        try {
            HttpGet httpGet = new HttpGet(GET_URL);
            HttpClientResponseHandler<Boolean> handler = response -> response.getCode() == 200;

            FutureTask<Boolean> futureTask = requestExecutionService.execute(httpGet, HttpClientContext.create(), handler);

            Boolean isOk = futureTask.get(1, TimeUnit.SECONDS);
            System.out.println("Is OK? : " + isOk);
        } finally {
            requestExecutionService.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TimeoutAwareHttpClient httpClient = new TimeoutAwareHttpClient();
        httpClient.executeAndSetTimeoutWithRequestConfig();
        httpClient.executeAndSetTimeoutWithRequestConfigPerRequest();
        httpClient.executeAndSetTimeoutWithFutureTask();
    }
}

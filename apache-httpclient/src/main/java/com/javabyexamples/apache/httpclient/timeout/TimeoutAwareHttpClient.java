package com.javabyexamples.apache.httpclient.timeout;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.HttpRequestFutureTask;
import org.apache.http.util.EntityUtils;

public class TimeoutAwareHttpClient {

    public void executeAndSetTimeoutWithRequestConfig() throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
          .setConnectionRequestTimeout(1000)
          .setConnectTimeout(1000)
          .setSocketTimeout(1000)
          .build();
        try (CloseableHttpClient httpClient = HttpClients.custom()
          .setDefaultRequestConfig(requestConfig)
          .build()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public void executeAndSetTimeoutWithRequestConfigPerRequest() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final RequestConfig requestConfig = RequestConfig.custom()
              .setConnectionRequestTimeout(1000)
              .setConnectTimeout(1000)
              .setSocketTimeout(1000)
              .build();
            final HttpGet httpGet = new HttpGet(GET_URL);
            httpGet.setConfig(requestConfig);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                EntityUtils.consumeQuietly(response.getEntity());
            }
        }
    }

    public void executeAndSetTimeoutWithFutureTask() throws Exception {
        HttpClient httpclient = HttpClientBuilder.create()
          .setMaxConnPerRoute(5)
          .setMaxConnTotal(5)
          .build();
        ExecutorService execService = Executors.newFixedThreadPool(5);
        FutureRequestExecutionService requestExecutionService = new FutureRequestExecutionService(httpclient, execService);
        try {
            HttpGet httpGet = new HttpGet(GET_URL);
            ResponseHandler<Boolean> handler = response -> response.getStatusLine().getStatusCode() == 200;

            HttpRequestFutureTask<Boolean> futureTask = requestExecutionService.execute(httpGet, HttpClientContext.create(), handler);

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

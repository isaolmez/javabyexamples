package com.javabyexamples.apache.httpclient5.asynclient;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.FutureRequestExecutionService;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.IOException;
import java.util.concurrent.*;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class AsyncHttpClient {

    public FutureRequestExecutionService getFutureExecutionService() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(5);
        connectionManager.setDefaultMaxPerRoute(5);
        final HttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(connectionManager)
                .build();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        return new FutureRequestExecutionService(httpClient, executorService);
    }

    public void executeAndReturnFuture() throws Exception {
        HttpClientResponseHandler<Integer> handler = new HttpClientResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(ClassicHttpResponse response) throws ClientProtocolException, IOException {
                return response.getCode();
            }
        };

        try (FutureRequestExecutionService executionService = getFutureExecutionService()) {
            HttpGet httpGet = new HttpGet(GET_URL);

            FutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler);

            int statusCode = futureTask.get();
            System.out.println("Status code: " + statusCode);
        }
    }

    public void executeAndCancelRequest() throws Exception {
        HttpClientResponseHandler<Integer> handler = new HttpClientResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(ClassicHttpResponse response) throws ClientProtocolException, IOException {
                return response.getCode();
            }
        };

        try (FutureRequestExecutionService executionService = getFutureExecutionService()) {
            HttpGet httpGet = new HttpGet(GET_URL);

            FutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler);

            futureTask.cancel(true);

            int statusCode = futureTask.get();
            System.out.println("Status code: " + statusCode);
        } catch (CancellationException e) {
            System.out.println("Planned cancellation");
        }
    }

    public void executeAndHandleWithTimeout() throws Exception {
        HttpClientResponseHandler<Integer> handler = new HttpClientResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(ClassicHttpResponse response) throws ClientProtocolException, IOException {
                return response.getCode();
            }
        };

        try (FutureRequestExecutionService executionService = getFutureExecutionService()) {
            HttpGet httpGet = new HttpGet(GET_URL);

            FutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler);

            int statusCode = futureTask.get(10, TimeUnit.SECONDS);
            System.out.println("Status code: " + statusCode);
        }
    }

    public void executeAndHandleWithCallback() throws Exception {
        HttpClientResponseHandler<Integer> handler = new HttpClientResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(ClassicHttpResponse response) throws ClientProtocolException, IOException {
                return response.getCode();
            }
        };

        FutureCallback<Integer> callback = new FutureCallback<Integer>() {
            @Override
            public void completed(Integer statusCode) {
                System.out.println("Completed successfully");
            }

            @Override
            public void failed(Exception e) {
                System.out.println("Failed: " + e.getMessage());
            }

            @Override
            public void cancelled() {
                System.out.println("Cancelled");
            }
        };

        try (FutureRequestExecutionService executionService = getFutureExecutionService()) {
            HttpGet httpGet = new HttpGet(GET_URL);

            FutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler, callback);

            int statusCode = futureTask.get(10, TimeUnit.SECONDS);
            System.out.println("Status code: " + statusCode);
        }
    }

    public static void main(String[] args) throws Exception {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.executeAndReturnFuture();
        httpClient.executeAndCancelRequest();
        httpClient.executeAndHandleWithTimeout();
        httpClient.executeAndHandleWithCallback();
    }
}

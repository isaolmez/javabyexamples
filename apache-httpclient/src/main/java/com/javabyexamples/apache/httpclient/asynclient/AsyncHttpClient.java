package com.javabyexamples.apache.httpclient.asynclient;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpRequestFutureTask;

public class AsyncHttpClient {

    public FutureRequestExecutionService getFutureExecutionService() {
        final HttpClient httpClient = HttpClientBuilder.create()
          .setMaxConnPerRoute(5)
          .setMaxConnTotal(5)
          .build();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        return new FutureRequestExecutionService(httpClient, executorService);
    }

    public void executeAndReturnFuture() throws Exception {
        ResponseHandler<Integer> handler = new ResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                return response.getStatusLine().getStatusCode();
            }
        };

        try (FutureRequestExecutionService executionService = getFutureExecutionService()) {
            HttpGet httpGet = new HttpGet(GET_URL);

            HttpRequestFutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler);

            int statusCode = futureTask.get();
            System.out.println("Status code: " + statusCode);
        }
    }

    public void executeAndCancelRequest() throws Exception {
        ResponseHandler<Integer> handler = new ResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                return response.getStatusLine().getStatusCode();
            }
        };

        try (FutureRequestExecutionService executionService = getFutureExecutionService()) {
            HttpGet httpGet = new HttpGet(GET_URL);

            HttpRequestFutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler);

            futureTask.cancel(true);

            int statusCode = futureTask.get();
            System.out.println("Status code: " + statusCode);
        } catch (CancellationException e) {
            System.out.println("Planned cancellation");
        }
    }

    public void executeAndHandleWithTimeout() throws Exception {
        ResponseHandler<Integer> handler = new ResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                return response.getStatusLine().getStatusCode();
            }
        };

        try (FutureRequestExecutionService executionService = getFutureExecutionService()) {
            HttpGet httpGet = new HttpGet(GET_URL);

            HttpRequestFutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler);

            int statusCode = futureTask.get(10, TimeUnit.SECONDS);
            System.out.println("Status code: " + statusCode);
        }
    }

    public void executeAndHandleWithCallback() throws Exception {
        ResponseHandler<Integer> handler = new ResponseHandler<Integer>() {
            @Override
            public Integer handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                return response.getStatusLine().getStatusCode();
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

            HttpRequestFutureTask<Integer> futureTask = executionService.execute(httpGet, HttpClientContext.create(), handler, callback);

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

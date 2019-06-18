package com.javabyexamples.apache.httpclient.retrying;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class RetryingHttpClient {

    public void executeRetryingThreeTimesImplicitly() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
          .addInterceptorLast(new HttpRequestInterceptor() {
              @Override
              public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                  throw new IOException("Planned");
              }
          })
          .build()) {

            executeGetRequest(httpClient);
        }
    }

    public void executeRetryingTenTimesExplicitly() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
          .addInterceptorLast(new HttpRequestInterceptor() {
              @Override
              public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                  throw new IOException("Planned");
              }
          })
          .setRetryHandler(new DefaultHttpRequestRetryHandler(10, false))
          .build()) {

            executeGetRequest(httpClient);
        }
    }

    public void executeWithDisablingRetries() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.custom()
          .addInterceptorLast(new HttpRequestInterceptor() {
              @Override
              public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                  throw new IOException("Planned");
              }
          })
          .disableAutomaticRetries()
          .build()) {

            executeGetRequest(httpClient);
        }
    }


    public void executeRetryingWithCustom() throws Exception {
        HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                return executionCount < 5;
            }
        };

        try (CloseableHttpClient httpClient = HttpClients.custom()
          .addInterceptorLast(new HttpRequestInterceptor() {
              @Override
              public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
                  throw new IOException("Planned");
              }
          })
          .setRetryHandler(requestRetryHandler)
          .build()) {

            executeGetRequest(httpClient);
        }
    }

    private void executeGetRequest(CloseableHttpClient httpClient) throws IOException {
        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
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

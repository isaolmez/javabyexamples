package com.javabyexamples.apache.httpclient.retrying;

import static com.javabyexamples.apache.httpclient.Constants.STATUS_400_URL;
import static com.javabyexamples.apache.httpclient.Constants.STATUS_404_URL;
import static com.javabyexamples.apache.httpclient.Constants.STATUS_500_URL;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

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
          .addInterceptorLast(new HttpResponseInterceptor() {
              @Override
              public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                  if (response.getStatusLine().getStatusCode() == 500) {
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
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
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

package com.javabyexamples.apache.httpclient.usingproxy;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;

public class ProxyUsingHttpClient {

    public void executeWithProxy() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom()
          .setProxy(new HttpHost("localhost", 8080))
          .build();
        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            handleResponse(response);
        }
    }

    public void executeWithProxyPerRequest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestConfig requestConfig = RequestConfig.custom()
          .setProxy(new HttpHost("localhost", 8080))
          .build();

        final HttpGet httpGet = new HttpGet(GET_URL);
        httpGet.setConfig(requestConfig);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            handleResponse(response);
        }
    }

    public void executeWithProxyAndRoute() throws Exception {
        HttpHost proxy = new HttpHost("some-proxy.com", 8080);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        CloseableHttpClient httpClient = HttpClients.custom()
          .setRoutePlanner(routePlanner)
          .build();

        final HttpGet httpGet = new HttpGet(GET_URL);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            handleResponse(response);
        }
    }

    public void executeAndSupplyProxyAuthentication() throws Exception {
        // TODO
    }

    private void handleResponse(CloseableHttpResponse response) {
        StatusLine statusLine = response.getStatusLine();
        System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
        EntityUtils.consumeQuietly(response.getEntity());
    }

    public static void main(String[] args) throws Exception {
        ProxyUsingHttpClient httpClient = new ProxyUsingHttpClient();
        httpClient.executeWithProxy();
        httpClient.executeWithProxyPerRequest();
        httpClient.executeWithProxyAndRoute();
        httpClient.executeAndSupplyProxyAuthentication();
    }
}

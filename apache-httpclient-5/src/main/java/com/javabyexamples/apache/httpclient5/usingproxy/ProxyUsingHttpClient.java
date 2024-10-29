package com.javabyexamples.apache.httpclient5.usingproxy;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

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
        System.out.println(response.getCode());
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

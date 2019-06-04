package com.javabyexamples.apache.httpclient.usingproxy;

import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

public class ProxyUsingHttpClient {

    public void executeWithProxy() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setProxy(new HttpHost("localhost", 8080))
                .build();
        final HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
        }
    }

    public void executeWithProxyPerRequest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final RequestConfig requestConfig = RequestConfig.custom()
                .setProxy(new HttpHost("localhost", 8080))
                .build();

        final HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        httpGet.setConfig(requestConfig);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
        }
    }

    public void executeWithProxyAndRoute() throws Exception {
        HttpHost proxy = new HttpHost("someproxy", 8080);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRoutePlanner(routePlanner)
                .build();

        final HttpGet httpGet = new HttpGet("http://httpbin.org/get");

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
        }
    }

    public void executeAndSupplyProxyAuthentication() throws Exception {
        // TODO
    }

    public static void main(String[] args) throws Exception {
        ProxyUsingHttpClient httpClient = new ProxyUsingHttpClient();
        httpClient.executeWithProxy();
        httpClient.executeWithProxyPerRequest();
        httpClient.executeWithProxyAndRoute();
        httpClient.executeAndSupplyProxyAuthentication();
    }
}

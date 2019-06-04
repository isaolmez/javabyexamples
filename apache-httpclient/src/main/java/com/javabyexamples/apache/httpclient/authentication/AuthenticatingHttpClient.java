package com.javabyexamples.apache.httpclient.authentication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class AuthenticatingHttpClient {

    public void executeWithBasicAuth() throws Exception {
        final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope("httpbin.org", 80),
                new UsernamePasswordCredentials("user", "pass"));
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        performRequest(httpClient, "http://httpbin.org/basic-auth/user/pass");
    }

    public void executeWithBasicAuthPreemptive() throws Exception {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope("httpbin.org", 80),
                new UsernamePasswordCredentials("user", "pass"));
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        HttpHost target = new HttpHost("httpbin.org", 80, "http");
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(target, basicAuth);

        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        final String requestUri = "http://httpbin.org/basic-auth/user/passwd";
        final HttpGet httpGet = new HttpGet(requestUri);
        try (CloseableHttpResponse response = httpClient.execute(target, httpGet, localContext);) {
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println("Response body: " + responseBody);
        }
    }

    private void performRequest(CloseableHttpClient httpClient, String uri) throws IOException {
        final HttpGet httpGet = new HttpGet(uri);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println("Response body: " + responseBody);
        }
    }

    public static void main(String[] args) throws Exception {
        AuthenticatingHttpClient httpClient = new AuthenticatingHttpClient();
//        httpClient.executeWithBasicAuth();
        httpClient.executeWithBasicAuthPreemptive();
    }
}
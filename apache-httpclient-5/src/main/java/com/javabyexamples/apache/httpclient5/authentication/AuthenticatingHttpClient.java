package com.javabyexamples.apache.httpclient5.authentication;

import org.apache.hc.client5.http.auth.AuthCache;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.auth.BasicAuthCache;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.auth.BasicScheme;
import org.apache.hc.client5.http.impl.auth.CredentialsProviderBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.javabyexamples.apache.httpclient5.Constants.HOSTNAME;
import static com.javabyexamples.apache.httpclient5.Constants.URL_FOR_BASIC_AUTH;

public class AuthenticatingHttpClient {

    public void executeWithBasicAuth() throws Exception {
        final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(HOSTNAME, 80),
                new UsernamePasswordCredentials("user", "pass".toCharArray()));
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
        performRequest(httpClient, URL_FOR_BASIC_AUTH);
    }

    public void executeWithBasicAuthPreemptive() throws Exception {
        CredentialsProvider credentialsProvider = CredentialsProviderBuilder.create()
                .add(new AuthScope(HOSTNAME, 80), new UsernamePasswordCredentials("user", "pass".toCharArray()))
                .build();
        CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        HttpHost target = new HttpHost("http", HOSTNAME, 80);
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(target, basicAuth);

        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        final String requestUri = URL_FOR_BASIC_AUTH;
        final HttpGet httpGet = new HttpGet(requestUri);
        try (CloseableHttpResponse response = httpClient.execute(target, httpGet, localContext);) {
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println("Response body: " + responseBody);
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    private void performRequest(CloseableHttpClient httpClient, String uri) throws IOException, ParseException {
        final HttpGet httpGet = new HttpGet(uri);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println("Response body: " + responseBody);
            EntityUtils.consumeQuietly(response.getEntity());
        }
    }

    public static void main(String[] args) throws Exception {
        AuthenticatingHttpClient httpClient = new AuthenticatingHttpClient();
        httpClient.executeWithBasicAuth();
        httpClient.executeWithBasicAuthPreemptive();
    }
}
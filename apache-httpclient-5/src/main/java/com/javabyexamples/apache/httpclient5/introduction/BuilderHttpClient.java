package com.javabyexamples.apache.httpclient5.introduction;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.nio.charset.StandardCharsets;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class BuilderHttpClient {

    public void executeGet() throws Exception {
        final CloseableHttpClient httpClient = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(RequestConfig.custom().setMaxRedirects(1).build())
                .build();

        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            System.out.println(response.getCode());

            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println(responseBody);
        }
    }

    public static void main(String[] args) throws Exception {
        BuilderHttpClient httpClient = new BuilderHttpClient();
        httpClient.executeGet();
    }
}

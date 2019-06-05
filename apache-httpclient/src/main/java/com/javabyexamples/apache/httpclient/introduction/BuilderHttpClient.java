package com.javabyexamples.apache.httpclient.introduction;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import java.nio.charset.StandardCharsets;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class BuilderHttpClient {

    public void executeGet() throws Exception {
        final CloseableHttpClient httpClient = HttpClientBuilder
          .create()
          .setDefaultRequestConfig(RequestConfig.custom().setMaxRedirects(1).build())
          .build();

        final HttpGet httpGet = new HttpGet(GET_URL);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());

            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            System.out.println(responseBody);
        }
    }

    public static void main(String[] args) throws Exception {
        BuilderHttpClient httpClient = new BuilderHttpClient();
        httpClient.executeGet();
    }
}

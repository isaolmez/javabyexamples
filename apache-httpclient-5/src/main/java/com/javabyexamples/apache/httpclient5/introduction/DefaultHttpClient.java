package com.javabyexamples.apache.httpclient5.introduction;

import com.javabyexamples.apache.httpclient5.Constants;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class DefaultHttpClient {

    public void executeGet() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                System.out.println(response.getCode());

                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                System.out.println("Response body: " + responseBody);
            }
        }
    }

    public void executeGetWithHeaders() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            httpGet.addHeader("HttpClient-Header", "test");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                System.out.println(response.getCode());

                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                System.out.println("Response body: " + responseBody);
            }
        }
    }

    public void handleStatusCodes() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            httpGet.addHeader("HttpClient-Header", "test");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getCode() != HttpStatus.SC_OK) {
                    System.out.println("Response is not OK");
                    EntityUtils.consumeQuietly(response.getEntity());
                }

                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Response body: " + responseBody);
            }
        }
    }

    public void executePost() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpPost httpPost = new HttpPost(GET_URL);

            final List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("name", "John"));
            nameValuePairs.add(new BasicNameValuePair("message", "Hello"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            try (final CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println(response.getCode());

                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                System.out.println("Response body: " + responseBody);
            }
        }
    }

    public void constructUri() throws Exception {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost(Constants.HOSTNAME)
                .setPath("/get")
                .setParameter("city", "London")
                .setParameter("count", "100")
                .build();
        System.out.println(uri.toString());
    }

    public void constructRequest() {
        ClassicHttpRequest getRequest = ClassicRequestBuilder.get()
                .setUri(GET_URL)
                .addParameter("city", "London")
                .addParameter("count", "100")
                .build();
        System.out.println(getRequest);
    }

    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.executeGet();
        httpClient.executeGetWithHeaders();
        httpClient.handleStatusCodes();
        httpClient.executePost();
        httpClient.constructUri();
        httpClient.constructRequest();
    }
}

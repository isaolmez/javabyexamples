package com.javabyexamples.apache.httpclient.introduction;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import com.javabyexamples.apache.httpclient.Constants;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class DefaultHttpClient {

    public void executeGet() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());

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
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());

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
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
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
                StatusLine statusLine = response.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());

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
        HttpUriRequest getRequest = RequestBuilder.get()
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

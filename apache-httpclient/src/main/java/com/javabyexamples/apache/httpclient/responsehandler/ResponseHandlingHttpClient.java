package com.javabyexamples.apache.httpclient.responsehandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ResponseHandlingHttpClient {

    public void executeGet() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet("http://httpbin.org/get");
        String responseBody = httpClient.execute(httpGet, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                StatusLine statusLine = httpResponse.getStatusLine();
                System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                if (statusLine.getStatusCode() != 200) {
                    EntityUtils.consumeQuietly(httpResponse.getEntity());
                    throw new RuntimeException("Response is not OK");
                }
                return EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
            }
        });

        System.out.println("Response body: " + responseBody);
    }

    public static void main(String[] args) throws Exception {
        ResponseHandlingHttpClient httpClient = new ResponseHandlingHttpClient();
        httpClient.executeGet();
    }
}

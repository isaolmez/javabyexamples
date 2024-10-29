package com.javabyexamples.apache.httpclient5.responsehandler;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.javabyexamples.apache.httpclient5.Constants.GET_URL;

public class ResponseHandlingHttpClient {

    public void executeGet() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            String responseBody = httpClient.execute(httpGet, new HttpClientResponseHandler<String>() {
                @Override
                public String handleResponse(ClassicHttpResponse httpResponse) throws IOException, ParseException {
                    System.out.println(httpResponse.getCode());
                    if (httpResponse.getCode() != HttpStatus.SC_OK) {
                        return null;
                    }
                    return EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
                }
            });

            System.out.println("Response body: " + responseBody);
        }
    }

    public static void main(String[] args) throws Exception {
        ResponseHandlingHttpClient httpClient = new ResponseHandlingHttpClient();
        httpClient.executeGet();
    }
}

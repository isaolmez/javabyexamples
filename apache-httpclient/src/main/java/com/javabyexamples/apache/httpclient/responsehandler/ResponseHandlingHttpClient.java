package com.javabyexamples.apache.httpclient.responsehandler;

import static com.javabyexamples.apache.httpclient.Constants.GET_URL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ResponseHandlingHttpClient {

    public void executeGet() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpGet httpGet = new HttpGet(GET_URL);
            String responseBody = httpClient.execute(httpGet, new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    StatusLine statusLine = httpResponse.getStatusLine();
                    System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
                    if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
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

package com.javabyexamples.apache.httpclient.cookiehandling;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CookieHandlingHttpClient {

    public void executePostAndListCookies() throws Exception {
        final BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom()
          .setDefaultCookieStore(cookieStore)
          .build();

        performRequest(cookieStore, httpClient, "https://www.google.com");

        performRequest(cookieStore, httpClient, "https://www.facebook.com");
    }

    private void performRequest(BasicCookieStore cookieStore, CloseableHttpClient httpClient, String s)
      throws URISyntaxException, IOException {
        HttpUriRequest getGoogle = RequestBuilder.get()
          .setUri(new URI(s))
          .build();
        try (final CloseableHttpResponse response = httpClient.execute(getGoogle)) {
            EntityUtils.consume(response.getEntity());

            List<Cookie> cookies = cookieStore.getCookies();
            cookies.stream().forEach(System.out::println);
        }
    }

    public static void main(String[] args) throws Exception {
        CookieHandlingHttpClient httpClient = new CookieHandlingHttpClient();
        httpClient.executePostAndListCookies();
    }
}

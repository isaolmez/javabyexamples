package com.javabyexamples.apache.httpclient5.cookiehandling;

import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class CookieHandlingHttpClient {

    public void executePostAndListCookies() throws Exception {
        final BasicCookieStore cookieStore = new BasicCookieStore();
        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build()) {

            performRequest(cookieStore, httpClient, "https://www.google.com");

            performRequest(cookieStore, httpClient, "https://www.facebook.com");
        }
    }

    private void performRequest(BasicCookieStore cookieStore, CloseableHttpClient httpClient, String url)
            throws URISyntaxException, IOException {
        ClassicHttpRequest getGoogle = ClassicRequestBuilder.get()
                .setUri(new URI(url))
                .build();
        try (final CloseableHttpResponse response = httpClient.execute(getGoogle)) {
            EntityUtils.consume(response.getEntity());

            List<Cookie> cookies = cookieStore.getCookies();
            cookies.stream().forEach(System.out::println);
        }
    }

    private void performRequestAndClearCookies(BasicCookieStore cookieStore, CloseableHttpClient httpClient, String url)
            throws URISyntaxException, IOException {
        ClassicHttpRequest getGoogle = ClassicRequestBuilder.get()
                .setUri(new URI(url))
                .build();
        try (final CloseableHttpResponse response = httpClient.execute(getGoogle)) {
            EntityUtils.consume(response.getEntity());

            cookieStore.clear();
        }
    }

    public static void main(String[] args) throws Exception {
        CookieHandlingHttpClient httpClient = new CookieHandlingHttpClient();
        httpClient.executePostAndListCookies();
    }
}

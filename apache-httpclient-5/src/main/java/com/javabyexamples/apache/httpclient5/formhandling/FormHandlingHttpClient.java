package com.javabyexamples.apache.httpclient5.formhandling;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.javabyexamples.apache.httpclient5.Constants.POST_URL;


public class FormHandlingHttpClient {

    public void postFormWithBuilder() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final ClassicHttpRequest postRequest = ClassicRequestBuilder.post()
                    .setUri(new URI(POST_URL))
                    .addParameter("username", "user1")
                    .addParameter("credentials", "pass1")
                    .build();
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                handleResponse(response);
            }
        }
    }

    public void postForm() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            List<NameValuePair> formParameters = new ArrayList<>();
            formParameters.add(new BasicNameValuePair("username", "user1"));
            formParameters.add(new BasicNameValuePair("credentials", "pass1"));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParameters, StandardCharsets.UTF_8);
            HttpPost httpPost = new HttpPost(POST_URL);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                handleResponse(response);
            }
        }
    }

    public void postFormWithFile() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            URL resource = Thread.currentThread().getContextClassLoader().getResource("notes/todo.txt");
            File file = new File(resource.getFile());
            FileEntity fileEntity = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));
            HttpPost httpPost = new HttpPost(POST_URL);
            httpPost.setEntity(fileEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                handleResponse(response);
            }
        }
    }

    public void postFormWithJson() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            StringEntity jsonEntity = new StringEntity("{\"java\":\"byexamples\"}", ContentType.APPLICATION_JSON);
            HttpPost httpPost = new HttpPost(POST_URL);
            httpPost.setEntity(jsonEntity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                handleResponse(response);
            }
        }
    }


    private void handleResponse(CloseableHttpResponse response) {
        System.out.println(response.getCode());
        EntityUtils.consumeQuietly(response.getEntity());
    }

    public static void main(String[] args) throws Exception {
        FormHandlingHttpClient httpClient = new FormHandlingHttpClient();
        httpClient.postForm();
        httpClient.postFormWithBuilder();
        httpClient.postFormWithFile();
        httpClient.postFormWithJson();
    }
}

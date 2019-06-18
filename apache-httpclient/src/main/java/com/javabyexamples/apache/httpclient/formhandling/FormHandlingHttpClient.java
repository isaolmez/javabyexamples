package com.javabyexamples.apache.httpclient.formhandling;

import static com.javabyexamples.apache.httpclient.Constants.POST_URL;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class FormHandlingHttpClient {

    public void postFormWithBuilder() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            final HttpUriRequest postRequest = RequestBuilder.post()
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
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParameters, Consts.UTF_8);
            HttpPost httpPost = new HttpPost(POST_URL);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                handleResponse(response);
            }
        }
    }

    public void postFormWithFile() throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            File file = new File("/notes/todo.txt");
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
        StatusLine statusLine = response.getStatusLine();
        System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
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

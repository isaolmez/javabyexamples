package com.javabyexamples.apache.httpclient.formhandling;

import static com.javabyexamples.apache.httpclient.Constants.POST_URL;

import com.javabyexamples.apache.httpclient.Constants;
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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class FormHandlingHttpClient {

    public void postFormWithBuilder() throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpUriRequest postRequest = RequestBuilder.post()
                .setUri(new URI(POST_URL))
                .addParameter("username", "user1")
                .addParameter("password", "pass1")
                .build();
        try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
        }
    }

    public void postForm() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> formParameters = new ArrayList<>();
        formParameters.add(new BasicNameValuePair("username", "user1"));
        formParameters.add(new BasicNameValuePair("password", "pass1"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParameters, Consts.UTF_8);
        HttpPost httpPost = new HttpPost(POST_URL);
        httpPost.setEntity(entity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
        }
    }

    public void postFormWithFile() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        File file = new File("/notes/todo.txt");
        FileEntity entity = new FileEntity(file, ContentType.create("text/plain", "UTF-8"));
        HttpPost httpPost = new HttpPost(POST_URL);
        httpPost.setEntity(entity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());
        }
    }


    public static void main(String[] args) throws Exception {
        FormHandlingHttpClient httpClient = new FormHandlingHttpClient();
        httpClient.postForm();
        httpClient.postFormWithBuilder();
        httpClient.postFormWithFile();
    }
}

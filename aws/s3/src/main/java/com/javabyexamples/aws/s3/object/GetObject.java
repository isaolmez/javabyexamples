package com.javabyexamples.aws.s3.object;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetObject {

    private final AmazonS3 amazonS3 = AmazonS3ClientBuilder.defaultClient();

    public void getObjectWithOverride(String bucketName, String key) throws IOException {
        S3Object headerOverrideObject = null;
        try {
            ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides()
              .withCacheControl("No-cache")
              .withContentDisposition("attachment; filename=example.txt");
            GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, key)
              .withResponseHeaders(headerOverrides);
            headerOverrideObject = amazonS3.getObject(getObjectRequestHeaderOverride);
            printInputStream(headerOverrideObject.getObjectContent());
        } catch (SdkClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (headerOverrideObject != null) {
                headerOverrideObject.close();
            }
        }
    }

    public void getObjectWithRange(String bucketName, String key) throws IOException {
        S3Object objectPortion = null;
        try {
            GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, key)
              .withRange(0, 9);
            objectPortion = amazonS3.getObject(rangeObjectRequest);
            System.out.println("Printing bytes retrieved.");
            printInputStream(objectPortion.getObjectContent());

        } catch (SdkClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (objectPortion != null) {
                objectPortion.close();
            }
        }
    }

    public void getObject(String bucketName, String key) throws IOException {
        S3Object fullObject = null;
        try {
            System.out.println("Downloading an object");
            fullObject = amazonS3.getObject(new GetObjectRequest(bucketName, key));
            System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
            System.out.println("Content: ");
            printInputStream(fullObject.getObjectContent());

        } catch (SdkClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } finally {
            if (fullObject != null) {
                fullObject.close();
            }
        }
    }

    private void printInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        final String bucketName = "javabyexamples";
        final String key = "hello.txt";
        final GetObject getObject = new GetObject();
//        getObject.getObject1(bucketName, key);
//        getObject.getObject2(bucketName, key);
        getObject.getObjectWithOverride(bucketName, key);
    }
}

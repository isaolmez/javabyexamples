package com.javabyexamples.aws.s3.object;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.DeleteVersionRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class ObjectOperations {

    private final AmazonS3 amazonS3 = AmazonS3ClientBuilder.defaultClient();

    public void deleteObject(String bucketName, String key) {
        try {
            final DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, key);
            amazonS3.deleteObject(deleteObjectRequest);
        } catch (SdkClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public void deleteObjectOnVersionedBucket(String bucketName, String key) {
        try {
            String bucketVersionStatus = amazonS3.getBucketVersioningConfiguration(bucketName).getStatus();
            if (!bucketVersionStatus.equals(BucketVersioningConfiguration.ENABLED)) {
                System.out.printf("Bucket %s is not versioning-enabled.", bucketName);
            } else {
                amazonS3.deleteObject(bucketName, key);
            }
        } catch (SdkClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    private void deleteVersion(String bucketName, String key, String versionId) {
        amazonS3.deleteVersion(new DeleteVersionRequest(bucketName, key, versionId));
    }

    private PutObjectResult createObject(String bucketName, String key) {
        return amazonS3.putObject(bucketName, key, "Hello AWS!");
    }

    public void recoverObject(String bucketName, String key) {
        final ListVersionsRequest request = new ListVersionsRequest()
          .withBucketName(bucketName)
          .withPrefix(key);
        VersionListing versionListing = amazonS3.listVersions(request);
        while (true) {
            for (S3VersionSummary s3VersionSummary : versionListing.getVersionSummaries()) {
                if (s3VersionSummary.isDeleteMarker()) {
                    amazonS3.deleteVersion(
                      new DeleteVersionRequest(bucketName, s3VersionSummary.getKey(), s3VersionSummary.getVersionId()));
                }
            }

            // Check whether there are more pages of versions to retrieve.
            if (versionListing.isTruncated()) {
                versionListing = amazonS3.listNextBatchOfVersions(versionListing);
            } else {
                break;
            }
        }
    }

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

    public static void main(String[] args) {
        final String bucketName = "javabyexamples-nonversioned";
        final String key = UUID.randomUUID().toString();
        final ObjectOperations objectOperations = new ObjectOperations();

        System.out.println("Create object: " + key);
        objectOperations.createObject(bucketName, key);
        System.out.println("Delete object: " + key);
        objectOperations.deleteObject(bucketName, key);


        final String versionedBucket = "javabyexamples";
        System.out.println("Create object with versioning: " + key);
        objectOperations.createObject(versionedBucket, key);
        System.out.println("Delete object with versioning: " + key);
        objectOperations.deleteObjectOnVersionedBucket(versionedBucket, key);

        System.out.println("Recovering object: " + key);
        objectOperations.recoverObject(versionedBucket, key);

        final String anotherKey = "test.txt";
        System.out.println("Create object with versioning: " + anotherKey);
        final PutObjectResult putObjectResult = objectOperations.createObject(versionedBucket, anotherKey);
        System.out.println("Delete object with versioning: " + anotherKey);
        objectOperations.deleteVersion(versionedBucket, anotherKey, putObjectResult.getVersionId());
    }
}

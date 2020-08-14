package com.javabyexamples.aws.s3.bucket;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.VersionListing;
import java.util.UUID;

public class BucketOperations {

    private final AmazonS3 amazonS3 = AmazonS3ClientBuilder.defaultClient();

    public void createBucket(String bucketName) {
        if (amazonS3.doesBucketExistV2(bucketName)) {
            System.out.println("Bucket already exists: " + bucketName);
            return;
        }

        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        amazonS3.createBucket(createBucketRequest);
    }

    public String getBucketLocation(String bucketName) {
        try {
            GetBucketLocationRequest getBucketLocationRequest = new GetBucketLocationRequest(bucketName);
            return amazonS3.getBucketLocation(getBucketLocationRequest);
        } catch (AmazonServiceException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return null;
        }
    }

    public void deleteBucket(String bucketName) {
        amazonS3.deleteBucket(bucketName);
    }

    public void emptyBucket(String bucketName) {
        System.out.println("Removing objects from bucket");
        ObjectListing objects = amazonS3.listObjects(bucketName);
        while (true) {
            for (S3ObjectSummary summary : objects.getObjectSummaries()) {
                amazonS3.deleteObject(bucketName, summary.getKey());
            }

            if (objects.isTruncated()) {
                objects = amazonS3.listNextBatchOfObjects(objects);
            } else {
                break;
            }
        }

        System.out.println("Removing versions from bucket");
        final ListVersionsRequest listVersionsRequest = new ListVersionsRequest().withBucketName(bucketName);
        VersionListing versions = amazonS3.listVersions(listVersionsRequest);
        while (true) {
            for (S3VersionSummary vs : versions.getVersionSummaries()) {
                amazonS3.deleteVersion(bucketName, vs.getKey(), vs.getVersionId());
            }

            if (versions.isTruncated()) {
                versions = amazonS3.listNextBatchOfVersions(versions);
            } else {
                break;
            }
        }

        System.out.println("OK, bucket ready to delete!");
        amazonS3.deleteBucket(bucketName);
    }

    public static void main(String[] args) {
        final BucketOperations bucketOperations = new BucketOperations();

        System.out.println("Create bucket.");
        final String bucketName = UUID.randomUUID().toString();
        bucketOperations.createBucket(bucketName);

        final String bucketLocation = bucketOperations.getBucketLocation(bucketName);
        System.out.println("Bucket location: " + bucketLocation);

        System.out.println("Delete bucket.");
        bucketOperations.emptyBucket(bucketName);

        System.out.println("Bucket location: " + bucketOperations.getBucketLocation(bucketName));
    }
}

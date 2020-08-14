package com.javabyexamples.aws.s3.versioning;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.DeleteVersionRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.S3VersionSummary;
import com.amazonaws.services.s3.model.SetBucketVersioningConfigurationRequest;
import com.amazonaws.services.s3.model.VersionListing;
import com.javabyexamples.aws.s3.bucket.BucketOperations;

public class VersioningOperations {

    public static String bucketName = "javabyexamples";
    private final AmazonS3 amazonS3 = AmazonS3ClientBuilder.defaultClient();

    public void enableVersioning(String bucketName) {
        try {
            // Enable versioning on the bucket.
            BucketVersioningConfiguration configuration = new BucketVersioningConfiguration()
              .withStatus(BucketVersioningConfiguration.ENABLED);

            SetBucketVersioningConfigurationRequest setBucketVersioningConfigurationRequest =
              new SetBucketVersioningConfigurationRequest(bucketName, configuration);

            amazonS3.setBucketVersioningConfiguration(setBucketVersioningConfigurationRequest);
        } catch (AmazonS3Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public void getVersioningConfiguration(String bucketName) {
        BucketVersioningConfiguration conf = amazonS3.getBucketVersioningConfiguration(bucketName);
        System.out.println("Bucket versioning configuration status: " + conf.getStatus());
    }

    public void suspendVersioning(String bucketName) {
        try {
            // Enable versioning on the bucket.
            BucketVersioningConfiguration configuration = new BucketVersioningConfiguration()
              .withStatus(BucketVersioningConfiguration.SUSPENDED);

            SetBucketVersioningConfigurationRequest setBucketVersioningConfigurationRequest =
              new SetBucketVersioningConfigurationRequest(bucketName, configuration);

            amazonS3.setBucketVersioningConfiguration(setBucketVersioningConfigurationRequest);
        } catch (AmazonS3Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    public void listVersions(String bucketName) {
        final ListVersionsRequest request = new ListVersionsRequest()
          .withBucketName(bucketName)
          .withMaxResults(2);
        VersionListing versionListing = amazonS3.listVersions(request);
        while (true) {
            for (S3VersionSummary s3VersionSummary : versionListing.getVersionSummaries()) {
                System.out.printf("Retrieved object %s, version %s\n",
                  s3VersionSummary.getKey(),
                  s3VersionSummary.getVersionId());
            }

            // Check whether there are more pages of versions to retrieve.
            if (versionListing.isTruncated()) {
                versionListing = amazonS3.listNextBatchOfVersions(versionListing);
            } else {
                break;
            }
        }
    }

    public void deleteVersion(String bucketName, String key, String versionId) {
        final DeleteVersionRequest deleteVersionRequest = new DeleteVersionRequest(bucketName, key, versionId);
        amazonS3.deleteVersion(deleteVersionRequest);
    }

    public static void main(String[] args) {
        final BucketOperations bucketOperations = new BucketOperations();
        bucketOperations.createBucket(bucketName);

        final VersioningOperations versioningOperations = new VersioningOperations();
        versioningOperations.enableVersioning(bucketName);
        versioningOperations.getVersioningConfiguration(bucketName);
        versioningOperations.suspendVersioning(bucketName);
        versioningOperations.getVersioningConfiguration(bucketName);
        versioningOperations.listVersions(bucketName);
    }
}

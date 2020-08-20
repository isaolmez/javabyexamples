package com.javabyexamples.aws.s3.subresources;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.BucketAccelerateConfiguration;
import com.amazonaws.services.s3.model.BucketCrossOriginConfiguration;
import com.amazonaws.services.s3.model.BucketLoggingConfiguration;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.GetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.model.GetBucketAclRequest;
import com.amazonaws.services.s3.model.GetBucketCrossOriginConfigurationRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;
import com.amazonaws.services.s3.model.GetBucketLoggingConfigurationRequest;
import com.amazonaws.services.s3.model.GetBucketVersioningConfigurationRequest;
import com.amazonaws.services.s3.model.Grant;

public class SubResourceOperations {

    private final AmazonS3 amazonS3 = AmazonS3ClientBuilder.defaultClient();

    public void bucketVersioningConfiguration(String bucketName) {
        final GetBucketVersioningConfigurationRequest bucketVersioningConfigurationRequest = new GetBucketVersioningConfigurationRequest(
          bucketName);
        final BucketVersioningConfiguration bucketVersioningConfiguration = amazonS3
          .getBucketVersioningConfiguration(bucketVersioningConfigurationRequest);
        System.out.println("Versioning status: " + bucketVersioningConfiguration.getStatus());
    }

    public void bucketLocation(String bucketName) {
        final GetBucketLocationRequest getBucketLocationRequest = new GetBucketLocationRequest(bucketName);
        final String bucketLocation = amazonS3.getBucketLocation(getBucketLocationRequest);
        System.out.println("Bucket location: " + bucketLocation);
    }

    public void bucketAccelerateConfiguration(String bucketName) {
        final BucketAccelerateConfiguration bucketAccelerateConfiguration = amazonS3
          .getBucketAccelerateConfiguration(new GetBucketAccelerateConfigurationRequest(bucketName));

        System.out.println("Accelerate status: " + bucketAccelerateConfiguration.getStatus());
    }

    public void acl(String bucketName) {
        final AccessControlList bucketAcl = amazonS3.getBucketAcl(new GetBucketAclRequest(bucketName));

        for (Grant grant : bucketAcl.getGrantsAsList()) {
            System.out.printf("Grantee: %s. Permission: %s%n", grant.getGrantee().getIdentifier(),
              grant.getPermission());
        }
    }

    public void bucketCrossOriginConfiguration(String bucketName) {
        final BucketCrossOriginConfiguration bucketCrossOriginConfiguration = amazonS3
          .getBucketCrossOriginConfiguration(new GetBucketCrossOriginConfigurationRequest(bucketName));
        System.out.println("Cross origin: " + bucketCrossOriginConfiguration);
    }

    public void bucketLoggingConfiguration(String bucketName) {
        final BucketLoggingConfiguration bucketLoggingConfiguration = amazonS3
          .getBucketLoggingConfiguration(new GetBucketLoggingConfigurationRequest(bucketName));
        System.out.println("Bucket logging: " + bucketLoggingConfiguration);
    }

    public static void main(String[] args) {
        final String bucketName = "javabyexamples";
        final SubResourceOperations subResourceOperations = new SubResourceOperations();
        subResourceOperations.bucketVersioningConfiguration(bucketName);

        subResourceOperations.bucketLocation(bucketName);

        subResourceOperations.bucketAccelerateConfiguration(bucketName);

        subResourceOperations.acl(bucketName);

        subResourceOperations.bucketCrossOriginConfiguration(bucketName);

        subResourceOperations.bucketLoggingConfiguration(bucketName);
    }
}

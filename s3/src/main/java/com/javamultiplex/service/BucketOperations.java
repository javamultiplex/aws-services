package com.javamultiplex.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

/**
 * @author Rohit Agarwal on 08/08/20 3:09 pm
 * @copyright www.javamultiplex.com
 */

@Slf4j
@Service
public class BucketOperations {

    private final S3Client s3Client;

    @Autowired
    public BucketOperations(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    /**
     * @param bucketName
     * @return
     */
    public void createBucket(String bucketName) {
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(Region.AP_SOUTH_1.id())
                        .build())
                .build();
        s3Client.createBucket(createBucketRequest);
    }

    /**
     * @return
     */
    public ListBucketsResponse listBuckets() {
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        return s3Client.listBuckets(listBucketsRequest);
    }

    /**
     * @param bucketName
     * @return
     */
    public void deleteEmptyBucket(String bucketName) {
        DeleteBucketRequest deleteBucketRequest = DeleteBucketRequest.builder()
                .bucket(bucketName)
                .build();
        s3Client.deleteBucket(deleteBucketRequest);
    }

    /**
     * @param bucketName
     * @return
     */
    public boolean isBucketExist(String bucketName) {
        boolean result = true;
        HeadBucketRequest headBucketRequest = HeadBucketRequest.builder()
                .bucket(bucketName)
                .build();
        try {
            s3Client.headBucket(headBucketRequest);
        } catch (S3Exception exception) {
            log.error("Exception occurred while checking bucket exist or not.", exception);
            switch (exception.statusCode()) {
                case 404:
                    log.info("Bucket [{}] not found" + bucketName);
                    break;
                case 403:
                    log.info("Insufficient permissions for Bucket [{}]", bucketName);
                    break;
                default:
                    log.error("Some other issue found");
                    break;
            }
            result = false;
        }
        return result;
    }
}
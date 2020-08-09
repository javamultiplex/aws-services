package com.javamultiplex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

/**
 * @author Rohit Agarwal on 08/08/20 4:40 pm
 * @copyright www.javamultiplex.com
 */
@Service
public class PutObject {

    private final S3Client s3Client;
    private final BucketOperations bucketOperations;

    @Autowired
    public PutObject(S3Client s3Client, BucketOperations bucketOperations) {
        this.s3Client = s3Client;
        this.bucketOperations = bucketOperations;
    }

    /**
     * @param bucket
     * @param object
     */
    public void putObject(String bucket, File object) {
        if (!bucketOperations.isBucketExist(bucket)) {
            bucketOperations.createBucket(bucket);
        }
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(object.getName())
                .build();
        s3Client.putObject(putObjectRequest, object.toPath());
    }
}

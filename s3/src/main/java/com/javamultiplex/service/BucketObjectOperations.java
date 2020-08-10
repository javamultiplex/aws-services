package com.javamultiplex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;

/**
 * @author Rohit Agarwal on 10/08/20 11:16 pm
 * @copyright www.javamultiplex.com
 */
@Service
public class BucketObjectOperations {

    private final S3Client s3Client;
    private final BucketOperations bucketOperations;

    @Autowired
    public BucketObjectOperations(S3Client s3Client, BucketOperations bucketOperations) {
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

    /**
     * @param bucket
     * @return
     */
    public ListObjectsResponse listObjects(String bucket) {
        ListObjectsRequest listObjectsRequest = ListObjectsRequest.builder()
                .bucket(bucket)
                .build();
        return s3Client.listObjects(listObjectsRequest);
    }

    /**
     * @param bucket
     * @param key
     * @return
     */
    public byte[] getObject(String bucket, String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return responseBytes.asByteArray();
    }

    /**
     * @param bucket
     * @param key
     * @return
     */
    public void deleteObject(String bucket, String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }

}

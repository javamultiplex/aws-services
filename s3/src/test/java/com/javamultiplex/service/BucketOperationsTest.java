package com.javamultiplex.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Rohit Agarwal on 08/08/20 3:58 pm
 * @copyright www.javamultiplex.com
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class BucketOperationsTest {
    @Autowired
    private BucketOperations bucketOperations;

    @Test
    @Order(1)
    public void shouldCreateBucket() {
        bucketOperations.createBucket("bucket-javamultiplex-12");
    }

    @Test
    @Order(2)
    public void shouldListBuckets() {
        ListBucketsResponse listBucketsResponse = bucketOperations.listBuckets();
        assertNotNull(listBucketsResponse);
        listBucketsResponse.buckets().forEach(bucket -> System.out.println(bucket.name()));
    }

    @Test
    @Order(3)
    public void shouldDeleteBucket() {
        bucketOperations.deleteEmptyBucket("bucket-javamultiplex-12");
    }

    @Test
    @Order((4))
    public void shouldCheckBucketExistOrNot() {
        boolean result = bucketOperations.isBucketExist("bucke-javamultiplex-12");
        assertFalse(result);
    }

}

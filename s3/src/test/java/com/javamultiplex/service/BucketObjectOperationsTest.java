package com.javamultiplex.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author Rohit Agarwal on 09/08/20 11:43 pm
 * @copyright www.javamultiplex.com
 */
@SpringBootTest
public class BucketObjectOperationsTest {

    private static final String BUCKET = "rohit-javamultiplex-1";

    @Autowired
    private BucketObjectOperations bucketObjectOperations;

    @Test
    public void shouldPutObject() throws IOException {
        File tempFile = File.createTempFile("abc", "xyz");
        Files.write(tempFile.toPath(), "Hello World again".getBytes());
        bucketObjectOperations.putObject(BUCKET, tempFile);
    }

    @Test
    public void shouldListObjects() {
        ListObjectsResponse listObjectsResponse = bucketObjectOperations.listObjects(BUCKET);
        List<S3Object> contents = listObjectsResponse.contents();
        contents.forEach(s3Object -> {
            String key = s3Object.key();
            System.out.println("Key : " + key);
            System.out.println("Owner : " + s3Object.owner());
            System.out.println(new String(bucketObjectOperations.getObject(BUCKET, key)));
            bucketObjectOperations.deleteObject(BUCKET, key);
        });
    }
}

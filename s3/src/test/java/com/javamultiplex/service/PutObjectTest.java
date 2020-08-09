package com.javamultiplex.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

/**
 * @author Rohit Agarwal on 09/08/20 11:43 pm
 * @copyright www.javamultiplex.com
 */
@SpringBootTest
public class PutObjectTest {

    @Autowired
    private PutObject putObject;

    @Test
    public void shouldPutObject() throws IOException {
        File tempFile = File.createTempFile("abc", "xyz");
        putObject.putObject("rohit" + System.currentTimeMillis(), tempFile);
    }
}

package com.javamultiplex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * @author Rohit Agarwal on 10/08/20 12:20 am
 * @copyright www.javamultiplex.com
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .credentialsProvider(ProfileCredentialsProvider.create())
                .region(Region.AP_SOUTH_1)
                .build();
    }
}

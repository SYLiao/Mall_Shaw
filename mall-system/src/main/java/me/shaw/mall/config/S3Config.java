package me.shaw.mall.config;

import com.fasterxml.jackson.annotation.JsonRawValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.access_key_id}")
    private String awsId;

    @Value("${aws.secret_access_key}")
    private String awsSecret;

    @Value("${aws.s3.region}")
    private String awsRegion;

    @Bean
    public S3Client s3Client(){
        System.out.println(awsId);
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsId, awsSecret);
        S3Client s3 = S3Client.builder().region(Region.of(awsRegion)).credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();
        return s3;
    }
}

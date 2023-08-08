package com.example.awscrtlibericaproblem.configuration

import com.example.awscrtlibericaproblem.S3TransferRunner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.transfer.s3.S3TransferManager

@EnableConfigurationProperties(AwsProperties::class)
@Configuration
class AwsCrtLibericaProblemConfiguration {

    @Bean
    fun transferManager(awsProperties: AwsProperties): S3TransferManager {
        val awsBasicCredentials = AwsBasicCredentials.create(awsProperties.accessKeyId, awsProperties.secretAccessKey)
        val s3AsyncClient = S3AsyncClient.crtBuilder()
            .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
            .region(Region.EU_WEST_1)
            .build()
        return S3TransferManager.builder()
            .s3Client(s3AsyncClient)
            .build()
    }

    @Bean
    fun runner(awsProperties: AwsProperties, s3TransferManager: S3TransferManager): CommandLineRunner {
        return S3TransferRunner(awsProperties, s3TransferManager)
    }
}

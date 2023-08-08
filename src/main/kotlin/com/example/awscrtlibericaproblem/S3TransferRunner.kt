package com.example.awscrtlibericaproblem

import com.example.awscrtlibericaproblem.configuration.AwsProperties
import org.springframework.boot.CommandLineRunner
import software.amazon.awssdk.services.s3.model.CopyObjectRequest
import software.amazon.awssdk.transfer.s3.S3TransferManager
import software.amazon.awssdk.transfer.s3.model.CopyRequest

class S3TransferRunner(private val awsProperties: AwsProperties, private val transferManager: S3TransferManager) :
    CommandLineRunner {
    override fun run(vararg args: String?) {
        val copyObjectRequest =
            CopyObjectRequest.builder()
                .sourceBucket(awsProperties.sourceBucket)
                .sourceKey(awsProperties.sourceKey)
                .destinationBucket(awsProperties.destinationBucket)
                .destinationKey(awsProperties.destinationKey)
                .build()
        transferManager.copy(
            CopyRequest.builder()
                .copyObjectRequest(copyObjectRequest)
                .build(),
        ).completionFuture().join()
        println("Transfer is complete")
    }
}
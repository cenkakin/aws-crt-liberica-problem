package com.example.awscrtlibericaproblem.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("aws")
data class AwsProperties(
    var sourceBucket: String = "",
    var sourceKey: String = "",
    var destinationBucket: String = "",
    var destinationKey: String = "",
    var accessKeyId: String = "",
    var secretAccessKey: String = "",
)
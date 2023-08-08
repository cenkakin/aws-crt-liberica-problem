package com.example.awscrtlibericaproblem

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy
import org.testcontainers.images.builder.ImageFromDockerfile
import java.time.Duration
import kotlin.io.path.Path

class AwsCrtLibericaProblemIntegrationIT {

    @Test
    fun liberica() {
        val regex = "\\[error occurred during error reporting \\(printing Java stack\\).*"
        assertDockerImageWithRegex("./Dockerfile-liberica", regex)
    }

//    @Test
//    fun corretto() {
//        val regex = ".*Transfer is complete.*"
//        assertDockerImageWithRegex("./Dockerfile-corretto", regex)
//    }

    private fun assertDockerImageWithRegex(dockerFilePath: String, regex: String) {
        Assertions.assertDoesNotThrow {
            val image = ImageFromDockerfile("aws-crt-s3-transfer-bug")
                .withDockerfile(Path(dockerFilePath))
            GenericContainer(image)
                .withStartupTimeout(Duration.ofMinutes(1))
                .apply {
                    setWaitStrategy(LogMessageWaitStrategy().withRegEx(regex))
                    start()
                }
        }
    }

}

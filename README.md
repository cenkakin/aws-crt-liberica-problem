This project is meant to demonstrate a bug that emerged in version 0.24.0 of `aws-crt-java` when using a liberica jvm image.

It's a simple spring boot project that performs `copy` operation from one bucket to another upon startup. (See [S3TransferRunner](src/main/kotlin/com/example/awscrtlibericaproblem/S3TransferRunner.kt))

To make it work, simply fill out [application.properties](src/main/resources/application.properties) file. After that, run `./mvnw clean verify` to confirm that the bug persists. It does that by asserting the process logs an error message and you can also check if the transfer was completed in your s3 buckets.

If you'd like to test the [corretto image](src/test/kotlin/com/example/awscrtlibericaproblem/AwsCrtLibericaProblemIntegrationIT.kt), simply uncomment it in the test and run it. The operation should execute successfully, and you should see the message "Transfer is complete" in the logs.



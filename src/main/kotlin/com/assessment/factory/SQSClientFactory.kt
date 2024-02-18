package com.assessment.factory

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Primary
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI


@Factory
class SQSClientFactory {
    @Bean
    @Primary
    fun SqsClient(): SqsClient {
        val region = Region.of("us-east-1")
        val credentials = AwsBasicCredentials.create("test", "test")
        return SqsClient.builder()
            .region(region)
            .credentialsProvider { credentials }
            .endpointOverride(URI("http://localhost.localstack.cloud:4566"))
            .build()
    }

}
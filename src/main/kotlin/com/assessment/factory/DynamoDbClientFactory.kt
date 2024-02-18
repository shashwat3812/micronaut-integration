package com.assessment.factory

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.annotation.Primary
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Factory
class DynamoDbClientFactory {
    @Bean
    @Primary
    fun dynamoDbClient(): DynamoDbClient {
        val region = Region.of("us-east-1")
        val credentials = AwsBasicCredentials.create("test", "test")
        return DynamoDbClient.builder()
            .region(region)
            .credentialsProvider { credentials }
            .endpointOverride(URI("http://localhost.localstack.cloud:4566"))
            .build()
    }
}
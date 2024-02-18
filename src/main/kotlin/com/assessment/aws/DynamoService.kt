package com.assessment.aws

import com.assessment.config.DynamoConfiguration
import com.assessment.entity.Entity
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException

@Singleton
@Requires(beans = [DynamoDbClient::class, DynamoConfiguration::class ])
@Primary
open class DynamoService<T: Entity>(
    internal val dynamoDbClient: DynamoDbClient,
    internal val dynamoConfiguration: DynamoConfiguration
) {

    fun existsTable(): Boolean {
        return try {
            dynamoDbClient.describeTable {
                it.tableName(dynamoConfiguration.tableName)
            }
            true
        } catch (e: ResourceNotFoundException) {
            false
        }
    }

}
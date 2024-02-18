package com.assessment.repository

import com.assessment.entity.ReportEntity
import jakarta.inject.Singleton
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*

@Singleton
class ReportRepositoryImpl(
    dynamoDbClient: DynamoDbClient,
    dynamoConfiguration: DynamoConfiguration
): DynamoRepository<ReportEntity>(dynamoDbClient, dynamoConfiguration), ReportsRepository {

    protected val PARTITION_KEY = "pk"
    override fun save(isbn: String, name: String): String {
        val id: String = java.util.UUID.randomUUID().toString()
        println("Generated id is: $id")
        val report = ReportEntity(id, isbn, name)
        return save(report)
    }

    protected fun save(report: ReportEntity): String {

        val item: MutableMap<String, AttributeValue> = hashMapOf(PARTITION_KEY to AttributeValue.builder().s(report.id).build(),
            "id" to AttributeValue.builder().s(report.id).build(),
            "isbn" to AttributeValue.builder().s(report.isbn).build(),
            "name" to AttributeValue.builder().s(report.name).build())

        println("updating table ${dynamoConfiguration.tableName}")
        val request = PutItemRequest.builder().tableName(dynamoConfiguration.tableName)
            .item(item)
            .build()

        val response: PutItemResponse = dynamoDbClient.putItem(request)

        return response.toString()

    }
}
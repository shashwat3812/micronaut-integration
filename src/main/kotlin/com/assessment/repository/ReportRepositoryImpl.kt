package com.assessment.repository


import com.assessment.aws.DynamoService
import com.assessment.config.DynamoConfiguration
import com.assessment.entity.ReportEntity
import jakarta.inject.Singleton
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse

@Singleton
class ReportRepositoryImpl(
    dynamoDbClient: DynamoDbClient,
    dynamoConfiguration: DynamoConfiguration
) : DynamoService<ReportEntity>(dynamoDbClient, dynamoConfiguration), ReportsRepository {

    protected val PARTITION_KEY = "pk"
    override fun save(reportJsonString: String): String {
        val id: String = java.util.UUID.randomUUID().toString()
        println("Generated id is: $id")
        val report = ReportEntity(id, reportJsonString)
        return save(report)
    }

    protected fun save(report: ReportEntity): String {

        val item: MutableMap<String, AttributeValue> = hashMapOf(
            PARTITION_KEY to AttributeValue.builder().s(report.id).build(),
            "reportJsonString" to AttributeValue.builder().s(report.reportJsonString).build()
        )

        println("updating table ${dynamoConfiguration.tableName}")
        val request = PutItemRequest.builder().tableName(dynamoConfiguration.tableName)
            .item(item)
            .build()

        val response: PutItemResponse = dynamoDbClient.putItem(request)

        return response.toString()

    }
}
package com.assessment.aws

import com.assessment.config.SQSConfiguration
import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import jakarta.inject.Singleton
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Singleton
@Requires(beans = [SqsClient::class, SQSConfiguration::class ])
@Primary
class SQSService(internal val SQSClient: SqsClient, internal val SQSConfiguration: SQSConfiguration) {

    fun sendMessage(message: String): String {

        val request = SendMessageRequest.builder().queueUrl(SQSConfiguration.reportsQueueUrl).messageBody(message).build()
        println("Attempting to send the message: $message to queue:${SQSConfiguration.reportsQueueUrl}")

        val response = SQSClient.sendMessage(request)
        println("SQS send message response: $response")

        return response.messageId().toString()
    }
}
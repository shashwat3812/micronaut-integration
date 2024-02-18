package com.assessment.config

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires
import jakarta.validation.constraints.NotBlank

@Requires(property = SQSConfiguration.PREFIX)
@ConfigurationProperties(SQSConfiguration.PREFIX)
interface SQSConfiguration {

    @get:NotBlank
    val reportsQueueUrl: String
    companion object {
        const val PREFIX = "sqs"
    }

}
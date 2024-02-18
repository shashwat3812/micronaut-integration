package com.assessment.config

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires
import jakarta.validation.constraints.NotBlank

@Requires(property = "dynamodb.reports-table")
@ConfigurationProperties("dynamodb.reports-table")
interface DynamoConfiguration {

    @get:NotBlank
    val tableName: String
}
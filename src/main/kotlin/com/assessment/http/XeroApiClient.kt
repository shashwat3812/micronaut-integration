package com.assessment.http

import com.assessment.models.XeroModels
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client(id = "xero")
interface XeroApiClient {

    @Post("\${xero.token.url}")
    fun getAccessToken(
        @Header(name = "Authorization") Authorization: String,
        @Header(name = "Content-Type") contentType: String,
        @Body requestBody: XeroModels.XeroAccessTokenRequest
    ): XeroModels.XeroAccessToken

    @Get("\${xero.api.url}/\${xero.executivesummary.resource}")
    fun getExecutiveSummaryReportAsString(
        @Header(name = "Authorization") Authorization: String
    ): String
}
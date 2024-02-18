package com.assessment.service

import com.assessment.config.XeroConfiguration
import com.assessment.errors.XeroAuthException
import com.assessment.http.XeroApiClient
import com.assessment.models.XeroModels
import jakarta.inject.Singleton
import java.util.*

@Singleton
class XeroAuthService(private val xeroConfiguration: XeroConfiguration, private val xeroApiClient: XeroApiClient) {


    fun getAuthToken(): String {

        try {
            val tokenHeader = StringBuilder()
                .append(xeroConfiguration.clientId)
                .append(":")
                .append((xeroConfiguration.clientSecret)).toString()

            val tokenRequestAuth = StringBuilder()
                .append("Basic ")
                .append(
                    Base64.getEncoder()
                        .encodeToString(tokenHeader.toByteArray())
                ).toString()


            val requestBody = XeroModels.XeroAccessTokenRequest(
                "client_credentials",
                arrayOf("accounting.reports.read")
            )

            val response =
                xeroApiClient.getAccessToken(tokenRequestAuth, "application/x-www-form-urlencoded", requestBody)

            return response.accessToken
        } catch (e: Exception) {
            throw XeroAuthException("Error while fetching access token from Xero")
        }
    }
}
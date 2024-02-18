package com.assessment.service

import com.assessment.http.XeroApiClient
import jakarta.inject.Singleton

@Singleton
class XeroReportService(private val xeroApiClient: XeroApiClient) {

    fun getExecutiveSummaryReport(accessToken: String): String {
        val getRequestAuth = StringBuilder()
            .append("Bearer ")
            .append(accessToken).toString()

        val report = xeroApiClient.getExecutiveSummaryReportAsString(getRequestAuth)
        return report
    }
}
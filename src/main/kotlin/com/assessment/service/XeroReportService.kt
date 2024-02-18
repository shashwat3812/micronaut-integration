package com.assessment.service

import com.assessment.errors.XeroGetReportsException
import com.assessment.http.XeroApiClient
import jakarta.inject.Singleton

@Singleton
class XeroReportService(private val xeroApiClient: XeroApiClient) {

    fun getExecutiveSummaryReport(accessToken: String): String {
        try {
            val getRequestAuth = StringBuilder()
                .append("Bearer ")
                .append(accessToken).toString()


            val report = xeroApiClient.getExecutiveSummaryReportAsString(getRequestAuth)
            return report
        } catch (e: Exception) {
            throw XeroGetReportsException("Error while fetching ExecutiveSummary report from Xero")
        }
    }
}
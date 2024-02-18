package com.assessment.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/reports")
@ExecuteOn(TaskExecutors.BLOCKING)
class XeroController(private val xeroAuthService: XeroAuthService, private val xeroReportService: XeroReportService) {

    @Get("/executive-summary")
    @Produces(MediaType.TEXT_PLAIN)
    fun getExecutiveSummary(): String {
        val accessToken = xeroAuthService.getAuthToken()

        val report = xeroReportService.getExecutiveSummaryReport(accessToken)

        return report
    }
}
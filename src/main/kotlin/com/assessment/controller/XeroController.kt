package com.assessment.controller

import com.assessment.aws.SQSService
import com.assessment.errors.XeroAuthException
import com.assessment.errors.XeroGetReportsException
import com.assessment.service.XeroAuthService
import com.assessment.service.XeroReportService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/reports")
@ExecuteOn(TaskExecutors.BLOCKING)
class XeroController(
    private val xeroAuthService: XeroAuthService,
    private val xeroReportService: XeroReportService,
    private val queueService: SQSService
) {

    @Get("/executive-summary")
    @Produces(MediaType.TEXT_PLAIN)
    fun getExecutiveSummary(): HttpResponse<String> {
        try {
            val accessToken = xeroAuthService.getAuthToken()

            val report = xeroReportService.getExecutiveSummaryReport(accessToken)

            println("Attempting to send the report to queue: $report")

            queueService.sendMessage(report)

            return HttpResponse.ok(report)
        } catch (e: XeroAuthException) {
            return HttpResponse.serverError(e.message)
        } catch (e: XeroGetReportsException) {
            return HttpResponse.serverError(e.message)
        } catch (e: Exception) {
            println(e.message)
            return HttpResponse.serverError("Server error while processing reports")
        }

    }
}
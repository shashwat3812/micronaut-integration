package com.assessment.lambda

import com.amazonaws.services.lambda.runtime.events.SQSEvent
import com.assessment.repository.ReportsRepository
import io.micronaut.function.aws.MicronautRequestHandler
import jakarta.inject.Inject

class SyncReports : MicronautRequestHandler<SQSEvent, Unit>() {

    @Inject
    lateinit var reportsRepository: ReportsRepository

    override fun execute(input: SQSEvent?): Unit {
        try {

            if (input != null) {
                for (record in input.records) {
                    val body = record.body
                    println("Message body $body")
                    val response = reportsRepository.save(body)
                    println(response)
                }
            }

        } catch (e: Error) {
            println("####### ERROR ####### is $e")
        }
    }
}
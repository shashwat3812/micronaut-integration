package com.assessment.lambda

import com.amazonaws.services.lambda.runtime.events.SQSEvent
import com.assessment.repository.ReportsRepository
import io.micronaut.function.aws.MicronautRequestHandler
import jakarta.inject.Inject
import java.io.IOException

class SyncReports: MicronautRequestHandler<SQSEvent, Unit>() {

    @Inject
    lateinit var reportsRepository: ReportsRepository

    override fun execute(input: SQSEvent?): Unit {
        try {
            val response = reportsRepository.save("Aman ISBN", "Name is Aman")

            println(response)
        } catch (e: IOException) {
            println(e)
        }
    }
}
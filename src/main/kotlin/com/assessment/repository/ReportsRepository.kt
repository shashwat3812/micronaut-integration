package com.assessment.repository

import io.micronaut.core.annotation.NonNull
import jakarta.validation.constraints.NotBlank

interface ReportsRepository {
    @NonNull
    fun save(@NonNull @NotBlank reportJsonString: String): String
}
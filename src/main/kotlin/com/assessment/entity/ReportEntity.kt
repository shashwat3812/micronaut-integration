package com.assessment.entity

import io.micronaut.core.annotation.NonNull
import io.micronaut.serde.annotation.Serdeable
import jakarta.validation.constraints.NotBlank

@Serdeable
class ReportEntity (@field:NonNull @field:NotBlank override val id: String,
                    @field:NonNull @field:NotBlank val isbn: String,
                    @field:NonNull @field:NotBlank val name: String): Entity {

}
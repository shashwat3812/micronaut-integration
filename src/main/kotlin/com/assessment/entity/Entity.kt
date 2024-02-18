package com.assessment.entity

import io.micronaut.core.annotation.NonNull

interface Entity {

    @get:NonNull
    val id: String
}
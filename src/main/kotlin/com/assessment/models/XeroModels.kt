package com.assessment.models

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.serde.annotation.Serdeable

class XeroModels {

    @Serdeable
    data class XeroAccessToken(
        @JsonProperty("token_type") val tokenType: String,
        @JsonProperty("access_token") val accessToken: String
    )

    @Serdeable
    data class XeroAccessTokenRequest(
        @JsonProperty("grant_type") val grant_type: String,
        @JsonProperty("scope") val scope: Array<String>
    )
}
package com.assessment.config

import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires

@ConfigurationProperties(XeroConfiguration.PREFIX)
@Requires(property = XeroConfiguration.PREFIX)
class XeroConfiguration {
    var clientId: String? = null
    var clientSecret: String? = null

    companion object {
        const val PREFIX = "xero"
    }
}
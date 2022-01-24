package com.schoolsify.suite.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidationResponseHead(
    @SerialName("data")
    var data: Data? = null,
    @SerialName("status")
    var status: Int? = null
)
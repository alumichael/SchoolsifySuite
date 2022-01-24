package com.schoolsify.suite.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("created_at")
    var createdAt: String? = null,
    @SerialName("domain")
    var domain: String? = null,
    @SerialName("id")
    var id: Int? = null,
    @SerialName("is_active")
    var isActive: Boolean? = null,
    @SerialName("logo")
    var logo: String? = null,
    @SerialName("logo_url")
    var logoUrl: String? = null,
    @SerialName("name")
    var name: String? = null,
    @SerialName("updated_at")
    var updatedAt: String? = null
)
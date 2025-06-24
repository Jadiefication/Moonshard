package io.jadiefication.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    @SerialName("project_id")
    val projectId: String,
    val slug: String,
    val title: String,
    val description: String
)
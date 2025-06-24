package io.jadiefication.json

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val project_id: String,
    val slug: String,
    val title: String,
    val description: String
)

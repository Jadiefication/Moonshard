package io.jadiefication.fuel

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet

data class Parameter(
    val name: String,
    val value: Any?
)

fun String.httpGet(builder: () -> List<Parameter>): Request {
    val parameters = builder().map { it.name to it.value }
    return this.httpGet(parameters)
}
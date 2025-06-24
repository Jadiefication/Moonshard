package io.jadiefication

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.choice
import com.github.kittinunf.fuel.core.Body
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import io.jadiefication.fuel.Parameter
import io.jadiefication.fuel.httpGet
import io.jadiefication.minecraft.Versions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.collections.lastOrNull

class Moonshard: CliktCommand() {

    private val options = Versions.entries.map { it.version }.toTypedArray()
    val version: String by option("--version", "-v").choice(*options).prompt("Minecraft version").help {
        return@help "The minecraft version of the mods"
    }
    val name: String by option("--name", "-n").prompt("Mod Name").help {
        return@help "The name of the mod you want to search for"
    }
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val modrinthSearch = "https://api.modrinth.com/v2/search"

    override fun run() {
        val category = Versions.valueOf("V$version".replace(".", "_")).profile.name.lowercase()
        val response = modrinthSearch.httpGet {
            val query = Parameter("query", name)
            val faucets = Parameter("facets", "[[\"project_type:mod\"],[\"categories:$category\"],[\"versions:$version\"]]")

            return@httpGet listOf(query, faucets)
        }.responseString().second
        val body = response.body().asString(response.headers[Headers.CONTENT_TYPE].lastOrNull())
        echo(body)

    }
}

fun main(args: Array<String>) = Moonshard().main(args)
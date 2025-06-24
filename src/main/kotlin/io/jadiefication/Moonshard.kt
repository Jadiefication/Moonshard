package io.jadiefication

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.choice
import io.jadiefication.minecraft.Versions
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob

class Moonshard: CliktCommand() {

    private val options = Versions.entries.map { it.version }.toTypedArray()
    val version: String by option("--version", "-v").choice(*options).prompt("Minecraft version").help {
        return@help "The minecraft version of the mods"
    }
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {

    }

    override fun run() {
        echo("test: $version")
    }
}

fun main(args: Array<String>) = Moonshard().main(args)
package io.jadiefication

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.choice
import io.jadiefication.minecraft.Versions

class Moonshard: CliktCommand() {

    private val options = Versions.entries.map { it.version }.toTypedArray()
    val version: String by option("--version", "-v").choice(*options).prompt("Minecraft version").help {
        return@help "The minecraft version of the mods"
    }

    override fun run() {
        echo("test: $version")
    }
}

fun main(args: Array<String>) = Moonshard().main(args)
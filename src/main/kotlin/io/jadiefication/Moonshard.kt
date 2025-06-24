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
import io.jadiefication.json.Project
import io.jadiefication.minecraft.Versions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.jline.terminal.TerminalBuilder
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
    private val idRegex = createRegex("project_id")
    private val slugRegex = createRegex("slug")
    private val titleRegex = createRegex("title")
    private val descriptionRegex = createRegex("description")

    override fun run() = runBlocking {
        val displayJob = launch {
            val body = handleGettingProjects()
            val projectInfo = projectList(body)
            printOutLists(projectInfo)
            /*while (true) {
                echo(body)
                delay(1000)
            }*/
        }

        val keypressJob = launch(Dispatchers.IO) {
            val terminal = TerminalBuilder.builder().system(true).jna(false).build()
            terminal.enterRawMode()
            val reader = terminal.reader()
            while (true) {
                val c = reader.read()
                if (c == 27 && reader.read() == 91 &&
                    reader.read() == 50 && reader.read() == 49 &&
                    reader.read() == 126
                ) {
                    terminal.close()
                    echo("F10 pressed. Exiting...")
                    kotlin.system.exitProcess(0)
                }
            }
        }

        displayJob.join()
        keypressJob.join()
    }

    private fun handleGettingProjects(): String {
        val category = Versions.valueOf("V$version".replace(".", "_")).profile.name.lowercase()
        val response = modrinthSearch.httpGet {
            val query = Parameter("query", name)
            val faucets = Parameter(
                "facets",
                "[[\"project_type:mod\"],[\"categories:$category\"],[\"versions:$version\"]]"
            )
            val limit = Parameter("limit", "90")

            return@httpGet listOf(query, faucets, limit)
        }.responseString().second
        return response.body().asString(response.headers[Headers.CONTENT_TYPE].lastOrNull())
            .substringBefore(",\"offset\":").substringAfter("\"hits\":")
    }

    private fun projectList(body: String): List<Project> {
        echo(body)
        val stuff = Json { ignoreUnknownKeys = true }.decodeFromString<List<Project>>(body)
        echo("$stuff M")
        return stuff
    }

    private fun createRegex(name: String): Regex {
        return Regex("\"$name\"\\s*:\\s*\"(.*?)\"")
    }

    private fun printOutLists(projects: List<Project>) {
        projects.forEach { project ->
            echo("""
            Modrinth - ${project.title}
                - ${project.description}
                
        """.trimIndent())
        }
    }
}

fun main(args: Array<String>) = Moonshard().main(args)
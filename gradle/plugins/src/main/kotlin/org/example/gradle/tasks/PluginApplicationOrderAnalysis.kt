package org.example.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class PluginApplicationOrderAnalysis : DefaultTask() {

    @get:InputFiles abstract val pluginSrcFolders: ConfigurableFileCollection

    @get:OutputFile abstract val pluginApplicationDiagram: RegularFileProperty

    @TaskAction
    fun analyse() {
        val pluginDependencies =
            pluginSrcFolders
                .associate { srcFolder ->
                    val pluginProjectName = srcFolder.parentFile.parentFile.parentFile.name
                    pluginProjectName to
                        (srcFolder.listFiles() ?: emptyArray())
                            .filter { it.name.endsWith(".gradle.kts") }
                            .associate { pluginFile ->
                                val pluginId = pluginFile.name.replaceFirst(".gradle.kts", "")
                                pluginId to
                                    pluginsBlock(pluginFile.readText())
                                        .lines()
                                        .filter { it.contains("id(") }
                                        .map { it.substring(it.indexOf("id(\"") + 4, it.indexOf("\")")) }
                            }
                }
                .filter { it.value.isNotEmpty() }

        val lineBreak = "\n            "
        pluginApplicationDiagram
            .get()
            .asFile
            .writeText(
                """
            @startuml

            ${pluginDependencies.map { (projectName, pluginIds) ->
            "package \"$projectName\" {$lineBreak" +
                    pluginIds.keys.joinToString("") { "agent \"$it\"$lineBreak" } +
                    "$lineBreak}"
        }.joinToString(lineBreak)}

            ${pluginDependencies.values.fold(emptyMap<String, List<String>>()) { a, b -> a + b }.filter {
            it.value.isNotEmpty()
        }.map {
                (from, to) -> to.joinToString("") { "\"$from\" --down--> \"$it\"$lineBreak" }
        }.joinToString(lineBreak)}
               
            @enduml
        """
                    .trimIndent()
            )

        logger.lifecycle("Diagram: ${pluginApplicationDiagram.get().asFile.absolutePath}")
    }

    private fun pluginsBlock(script: String) =
        script.indexOf("}").let {
            when (it) {
                -1 -> ""
                else -> script.substring(0, it)
            }
        }
}

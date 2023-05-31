package org.example.dependencyanalysis

import com.autonomousapps.AbstractPostProcessingTask
import com.autonomousapps.model.Advice
import com.autonomousapps.model.ProjectCoordinates
import org.gradle.api.tasks.TaskAction

/**
 * Task that uses the 'com.autonomousapps.dependency-analysis' plugin to find unused dependencies and check
 * 'api' vs. 'implementation' scopes.
 */
abstract class DependencyScopeCheck : AbstractPostProcessingTask() {

    @TaskAction
    fun check() {
        val projectAdvice = projectAdvice().dependencyAdvice
        if (projectAdvice.isNotEmpty()) {
            val toAdd = projectAdvice.filter { it.toConfiguration != null && it.toConfiguration != "runtimeOnly" }
                .map { it.declaration(it.toConfiguration) }.sorted()
            val toRemove =
                projectAdvice.filter { it.fromConfiguration != null }.map { it.declaration(it.fromConfiguration) }
                    .sorted()

            throw RuntimeException(
                """
                    ${projectAdvice().projectPath.substring(1)}/build.gradle.kts
                    
                    Please add the following dependency declarations: 
                        ${toAdd.joinToString("\n                        ") { it }}
                    
                    Please remove the following dependency declarations: 
                        ${toRemove.joinToString("\n                        ") { it }}
                """.trimIndent()
            )
        }
    }

    private fun Advice.declaration(conf: String?) =
        if (coordinates is ProjectCoordinates) "${conf}(project(\"${coordinates.identifier}\"))"
        else "${conf}(\"${coordinates.identifier}\")"
}

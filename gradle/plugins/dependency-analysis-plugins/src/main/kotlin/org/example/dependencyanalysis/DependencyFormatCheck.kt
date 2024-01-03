package org.example.dependencyanalysis

import org.gradle.api.DefaultTask
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

/**
 * Check that 'dependencies' are defined in alphabetical order and without version.
 */
abstract class DependencyFormatCheck : DefaultTask() {

    @get:Input
    abstract val buildFilePath : Property<String>

    @get:Input
    abstract val declaredDependencies : MapProperty<String, List<String>> // Map of 'scope' to 'coordinates'

    @get:Input
    abstract val declaredConstraints : MapProperty<String, List<String>> // Map of 'scope' to 'coordinates'

    @TaskAction
    fun check() {
        declaredDependencies.get().forEach { (scope, dependencies) ->
            val declaredInBuildFile = dependencies.filter {
                // Ignore dependencies that are defined in our plugins
                it !in listOf(
                    "org.example.product:platform",
                    "org.slf4j:slf4j-simple",
                    "org.junit.jupiter:junit-jupiter-engine",
                    "org.junit.jupiter:junit-jupiter")
            }
            val sortedProject = declaredInBuildFile.filter { it.startsWith(":") }.sorted()
            val sortedExternal = declaredInBuildFile.filter { !it.startsWith(":") }.sorted()
            if (declaredInBuildFile != sortedProject + sortedExternal) {
                throw RuntimeException("""
                    ${buildFilePath.get()}
                    
                    Module versions are not declared in alphabetical order. Please use this order:
                        ${sortedProject.joinToString("\n                        ") {"${scope}(project(\"${it}\"))"}}
                        ${sortedExternal.joinToString("\n                        ") {"${scope}(\"${it}\")"}}
                """.trimIndent())
            }
        }

        declaredConstraints.get().forEach { (scope, constraints) ->
            val sortedConstraints = constraints.sorted()
            if (constraints != sortedConstraints) {
                throw RuntimeException("""
                    ${buildFilePath.get()}
                    
                    $scope dependency constraints are not declared in alphabetical order. Please use this order:
                        ${sortedConstraints.joinToString("\n                        ")}
                """.trimIndent())
            }
        }
    }
}

package org.example.dependencyanalysis

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.result.ResolvedComponentResult
import org.gradle.api.provider.Property
import org.gradle.api.provider.SetProperty
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

/**
 * Checks if new versions are available for what is declared in the platform.
 */
abstract class DependencyVersionUpgradesCheck : DefaultTask() {

    @get:Internal
    abstract val projectName: Property<String>

    @get:Internal
    abstract val apiDependencies: SetProperty<String>

    @get:Internal
    abstract val apiDependencyConstraints: SetProperty<String>

    @get:Internal
    abstract val latestReleasesResolutionResult: SetProperty<ResolvedComponentResult>

    @TaskAction
    fun check() {
        val platformDependencyUpgrades = apiDependencies.get().filter { declared -> declared.resolvedVersion() != declared.version() }
        val constraintUpgrades = apiDependencyConstraints.get().filter { declared -> declared.resolvedVersion() != declared.version() }
        if (platformDependencyUpgrades.isNotEmpty() || constraintUpgrades.isNotEmpty()) {
            throw RuntimeException("""
                    ${projectName.get()}/build.gradle.kts
                    
                    The following dependency versions should be upgraded in 'gradle/platform/build.gradle.kts' (dependencies {} block):
                    
                        ${platformDependencyUpgrades.joinToString("\n                        ") { "api(platform(\"${it.ga()}:${it.resolvedVersion()}\"))" }}    
                    
                    The following dependency versions should be upgraded in 'gradle/platform/build.gradle.kts' (dependencies.constraints {} block):
                    
                        ${constraintUpgrades.joinToString("\n                        ") { "api(\"${it.ga()}:${it.resolvedVersion()}\")" }}
                    
                    If we cannot perform an upgrade, please add a '{ version { reject("...") } }' statement and a comment
                    for the versions we cannot support at the moment.
                """.trimIndent())
        }
    }

    private fun String.ga() = substring(0, lastIndexOf(":"))

    private fun String.version() = substring(lastIndexOf(":") + 1)

    private fun String.resolvedVersion() =
        latestReleasesResolutionResult.get().find {
            it.moduleVersion!!.module.toString() == ga()
        }?.moduleVersion?.version ?: version() // if no fitting version could be determined, return the declared one

}

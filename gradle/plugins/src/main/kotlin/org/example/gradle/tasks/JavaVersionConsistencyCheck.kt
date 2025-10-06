package org.example.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.artifacts.result.ResolvedComponentResult
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.SetProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/** Check that all versions declared in a java-platform build.gradle.kts file are actually used. */
abstract class JavaVersionConsistencyCheck : DefaultTask() {

    /** The versions declared in the build.gradle.kts file. */
    @get:Input abstract val definedVersions: MapProperty<String, String>

    /** The aggregated classpath of all modules using the versions to resolve their dependencies. */
    @get:Input abstract val aggregatedClasspath: SetProperty<ResolvedComponentResult>

    /**
     * List of versions to ignore. This may be needed if versions for components that are not part of the runtime module
     * path of the applications are managed.
     */
    @get:Input abstract val excludes: ListProperty<String>

    /** The report TXT file that will contain the issues found. */
    @get:OutputFile abstract val reportFile: RegularFileProperty

    @TaskAction
    fun compare() {
        var issues = ""
        definedVersions.get().forEach { (id, version) ->
            val resolved =
                aggregatedClasspath.get().find {
                    val resolvedId = it.id
                    resolvedId is ModuleComponentIdentifier && resolvedId.moduleIdentifier.toString() == id
                }
            if (resolved == null) {
                if (!excludes.get().contains(id)) {
                    issues += "Not used: $id:$version\n"
                }
            } else {
                val resolvedVersion = resolved.moduleVersion?.version
                if (resolvedVersion != version) {
                    issues += "Wrong version: $id (declared=$version; used=$resolvedVersion)\n"
                }
            }
        }

        reportFile.get().asFile.writeText(issues)

        if (!issues.isEmpty()) {
            throw RuntimeException(issues)
        }
    }
}

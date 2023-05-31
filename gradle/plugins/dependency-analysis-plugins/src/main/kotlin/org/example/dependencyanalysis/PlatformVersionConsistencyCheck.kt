package org.example.dependencyanalysis

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.component.ModuleComponentSelector
import org.gradle.api.artifacts.result.ResolvedComponentResult
import org.gradle.api.attributes.Attribute
import org.gradle.api.attributes.Category
import org.gradle.api.provider.SetProperty
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

/**
 * Checks that all versions declared in the platform are used and are consistent with the consistent resolution result.
 */
abstract class PlatformVersionConsistencyCheck : DefaultTask() {

    @get:Internal
    abstract val productClasspath: SetProperty<ResolvedComponentResult>

    @get:Internal
    abstract val classpathFromPlatform: SetProperty<ResolvedComponentResult>

    @TaskAction
    fun check() {
        val publishedCategory = Attribute.of( Category.CATEGORY_ATTRIBUTE.name, String::class.java)
        val resolvedToDeclaredVersions =
            productClasspath.get().filter { it.moduleVersion?.group != "org.example.product" }.associate { cpEntry ->
                cpEntry.moduleVersion to cpEntry.dependents.find {
                    it.from.variants.any { variant ->
                        variant.attributes.getAttribute(Category.CATEGORY_ATTRIBUTE)?.name == Category.REGULAR_PLATFORM
                                || variant.attributes.getAttribute(publishedCategory) == Category.REGULAR_PLATFORM
                    }
                }?.let {
                    (it.requested as ModuleComponentSelector).version
                }
            }


        val unnecessaryEntries = classpathFromPlatform.get().filter { platformEntry ->
            productClasspath.get().none { platformEntry.moduleVersion?.module == it.moduleVersion?.module }
        }
        val missingEntries = resolvedToDeclaredVersions.filter { it.value == null }.map { it.key }
        val wrongEntries = resolvedToDeclaredVersions.filter { it.value != null && it.key?.version != it.value }

        if (unnecessaryEntries.isNotEmpty() || missingEntries.isNotEmpty() || wrongEntries.isNotEmpty()) {
            throw RuntimeException("""
                    The following entries are not used in production code:
                    
                        ${unnecessaryEntries.joinToString("\n                        ") { "api(\"${it.moduleVersion}\")" }}

                    The following transitive dependencies are not listed in the platform:
                    
                        ${missingEntries.joinToString("\n                        ") { "api(\"${it}\")" }}

                    The following dependencies should be updated to the resolved versions:
                    
                        ${wrongEntries.keys.joinToString("\n                        ") { "api(\"${it}\") - currently declared '${wrongEntries[it]}'" }}
                    
                    """.trimIndent())
        }
    }
}


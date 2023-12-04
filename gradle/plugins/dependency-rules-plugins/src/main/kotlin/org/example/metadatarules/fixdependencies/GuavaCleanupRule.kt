package org.example.metadatarules.fixdependencies

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

/**
 * Removes dependencies of Guava that we do not need and that are not Java Modules
 */
@CacheableRule
abstract class GuavaCleanupRule : ComponentMetadataRule {
    companion object {
        const val GUAVA_MODULE = "com.google.guava:guava"
    }

    override fun execute(context: ComponentMetadataContext) {
        context.details.allVariants {
            withDependencies {
                removeIf { it.name == "failureaccess" }
                removeIf { it.name == "j2objc-annotations" }
                removeIf { it.name == "checker-qual" }
                removeIf { it.name == "error_prone_annotations" }
            }
        }
    }
}

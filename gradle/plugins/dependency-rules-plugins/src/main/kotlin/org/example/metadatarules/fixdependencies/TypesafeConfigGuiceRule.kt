package org.example.metadatarules.fixdependencies

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

/**
 * Removes the 'no_aop' classifier from the Guice dependency, because it
 * no longer exists with Guice 5.0 to which we upgrade the dependency.
 */
@CacheableRule
abstract class TypesafeConfigGuiceRule : ComponentMetadataRule {
    companion object {
        const val TYPESAFE_CONFIG_GUICE_MODULE = "com.github.racc:typesafeconfig-guice"
    }

    override fun execute(context: ComponentMetadataContext) {
        context.details.allVariants {
            withDependencies {
                removeIf { it.group == "com.google.inject" }
                add("com.google.inject:guice")
            }
        }
    }
}

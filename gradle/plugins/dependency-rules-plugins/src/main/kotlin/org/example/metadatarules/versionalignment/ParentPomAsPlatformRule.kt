package org.example.metadatarules.versionalignment

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

/**
 * Sometime libraries consisting of multiple components do not offer a BOM but have
 * a 'Parent POM' that contains the information for alignment.
 * However, these often also contain constraints on other components that we do not
 * want to include. This Rule removes these additional constraints.
 */
@CacheableRule
abstract class ParentPomAsPlatformRule : ComponentMetadataRule {
    override fun execute(context: ComponentMetadataContext) {
        val group = context.details.id.group
        context.details.allVariants {
            withDependencyConstraints {
                removeAll { it.group != group }
            }
        }
    }
}

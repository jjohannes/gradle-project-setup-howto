package org.example.metadatarules.versionalignment

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

/**
 * Extend the central 'poi' component with constraints so that it can be used as platform
 * to align the versions of all 'org.apache.poi' components.
 *
 * See:
 * https://repo1.maven.org/maven2/org/apache/poi/poi/5.2.0/poi-5.2.0.pom
 */
@CacheableRule
abstract class PoiPlatformRule : ComponentMetadataRule {
    companion object {
        const val POI_COMMON = "org.apache.poi:poi"
    }

    override fun execute(context: ComponentMetadataContext) {
        val version = context.details.id.version
        context.details.allVariants {
            withDependencyConstraints {
                add("org.apache.poi:poi-excelant:$version")
                add("org.apache.poi:poi-ooxml:$version")
                add("org.apache.poi:poi-scratchpad:$version")
            }
        }
    }
}
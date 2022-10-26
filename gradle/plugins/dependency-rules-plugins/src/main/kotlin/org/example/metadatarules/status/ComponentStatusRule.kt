package org.example.metadatarules.status

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

/**
 * Make sure versions that are not final released have a different status than 'release'.
 * Otherwise, they will be considered when asking for the 'latest.release' version.
 *
 * (POM metadata does not support the 'status' concept and thus Gradle assumes everything is a 'release' by default)
 */
@CacheableRule
abstract class ComponentStatusRule : ComponentMetadataRule {
    override fun execute(context: ComponentMetadataContext) {
        val version = context.details.id.version
        val lcVersion = version.lowercase()
        if (lcVersion.contains("alpha")
            || lcVersion.contains("-b")
            || lcVersion.contains("beta")
            || lcVersion.contains("cr")
            || lcVersion.contains("m")
            || lcVersion.contains("rc")
            || lcVersion.startsWith("200")) {

            context.details.status = "integration"
        }
    }
}

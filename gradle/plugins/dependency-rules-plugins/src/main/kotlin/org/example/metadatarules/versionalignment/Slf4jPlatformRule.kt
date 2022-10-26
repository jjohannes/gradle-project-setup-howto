package org.example.metadatarules.versionalignment

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

/**
 * Extend the Parent POM 'slf4j-parent' with constraints so that it can be used as platform
 * to align the versions of all 'org.slf4j' components.
 *
 * See:
 * https://repo1.maven.org/maven2/org/slf4j/slf4j-parent/1.7.36/slf4j-parent-1.7.36.pom
 */
@CacheableRule
abstract class Slf4jPlatformRule : ComponentMetadataRule {
    companion object {
        const val SLF4J_PARENT = "org.slf4j:slf4j-parent"
    }

    override fun execute(context: ComponentMetadataContext) {
        val version = context.details.id.version
        context.details.allVariants {
            withDependencyConstraints {
                add("org.slf4j:slf4j-api:$version")
                add("org.slf4j:slf4j-simple:$version")
                add("org.slf4j:slf4j-nop:$version")
                add("org.slf4j:slf4j-jdk14:$version")
                add("org.slf4j:slf4j-log4j12:$version")
                add("org.slf4j:slf4j-reload4j:$version")
                add("org.slf4j:slf4j-jcl:$version")
                add("org.slf4j:slf4j-android:$version")
                add("org.slf4j:slf4j-ext:$version")
                add("org.slf4j:jcl-over-slf4j:$version")
                add("org.slf4j:log4j-over-slf4j:$version")
                add("org.slf4j:jul-to-slf4j:$version")
                add("org.slf4j:osgi-over-slf4j:$version")
            }
        }
    }
}
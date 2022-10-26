package org.example.metadatarules.versionalignment

import org.gradle.api.artifacts.CacheableRule
import org.gradle.api.artifacts.ComponentMetadataContext
import org.gradle.api.artifacts.ComponentMetadataRule

/**
 * Extend the Parent POM 'httpcomponents-client' with constraints so that it can be used as platform
 * to align the versions of all 'org.apache.httpcomponents' components.
 *
 * See:
 * https://repo1.maven.org/maven2/org/apache/httpcomponents/httpcomponents-client/4.5.13/httpcomponents-client-4.5.13.pom
 *
 * <modules>
 *   <module>httpclient</module>
 *   <module>httpmime</module>
 *   <module>fluent-hc</module>
 *   <module>httpclient-cache</module>
 *   <module>httpclient-win</module>
 *   <module>httpclient-osgi</module>
 * </modules>
 */
@CacheableRule
abstract class HttpComponentsPlatformRule : ComponentMetadataRule {
    companion object {
        const val HTTP_COMPONENTS_CLIENT = "org.apache.httpcomponents:httpcomponents-client"
    }

    override fun execute(context: ComponentMetadataContext) {
        val version = context.details.id.version
        context.details.allVariants {
            withDependencyConstraints {
                add("org.apache.httpcomponents:httpclient:$version")
                add("org.apache.httpcomponents:httpmime:$version")
                add("org.apache.httpcomponents:fluent-hc:$version")
                add("org.apache.httpcomponents:httpclient-cache:$version")
                add("org.apache.httpcomponents:httpclient-win:$version")
                add("org.apache.httpcomponents:httpclient-osgi:$version")
            }
        }
    }
}
package org.example.gradle.test

import org.assertj.core.api.Assertions.assertThat
import org.example.gradle.test.fixtures.GradleProject
import org.gradle.testkit.runner.TaskOutcome.SUCCESS
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File

class ConventionPluginTest {

    @ParameterizedTest
    @MethodSource("pluginIds")
    fun `each plugin can be applied individually without error`(pluginId: String) {
        val p = GradleProject()
        when {
            pluginId.endsWith(".settings") -> p.settingsFile("""plugins { id("${pluginId.substringBeforeLast(".settings")}") }""")
            pluginId.endsWith(".root") -> p.rootBuildFile("""plugins { id("${pluginId.substringBeforeLast(".settings")}") }""")
            else -> p.withMinimalStructure().moduleBuildFile("""plugins { id("$pluginId") }""")
        }

        val result = p.help()

        assertThat(result.task(":help")!!.outcome).isEqualTo(SUCCESS)

    }

    @Test
    fun `qualityGate sorts dependencies of a library`() {
        val p = GradleProject().withMinimalStructure()
        p.catalog("""
            [libraries]
            resteasy-core = { module = "org.jboss.resteasy:resteasy-core", version = "4.7.6.Final" }
            resteasy-jackson2-provider = { module = "org.jboss.resteasy:resteasy-jackson2-provider" }
            guice = { module = "com.google.inject:guice", version = "5.1.0" }
        """.trimIndent())
        val buildFile = p.moduleBuildFile("""
            plugins {
                id("org.example.gradle.component.library")
            }
            
            dependencies {
                implementation(libs.resteasy.jackson2.provider)
                api(libs.resteasy.core)
                implementation(libs.guice)
            }   
        """.trimIndent())
        p.file("module/src/main/java/foo/Bar.java", """
            package foo;
            public class Bar {
                private org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider a;
                private com.google.inject.Guice b;
                public org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap c;
            }
        """.trimIndent())

        p.qualityGate()

        assertThat(buildFile).hasContent("""
            plugins { id("org.example.gradle.component.library") }
            
            dependencies {
                api(libs.resteasy.core)
                implementation(libs.guice)
                implementation(libs.resteasy.jackson2.provider)
            }
        """.trimIndent())
    }

    @Test
    fun `qualityGate fails for wrong dependency scopes`() {
        val p = GradleProject().withMinimalStructure()
        p.catalog("""
            [libraries]
            resteasy-core = { module = "org.jboss.resteasy:resteasy-core", version = "4.7.6.Final" }
            resteasy-jackson2-provider = { module = "org.jboss.resteasy:resteasy-jackson2-provider" }
            guice = { module = "com.google.inject:guice", version = "5.1.0" }
        """.trimIndent())
        val buildFile = p.moduleBuildFile("""
            plugins {
                id("org.example.gradle.component.library")
            }
            
            dependencies {
                implementation(libs.resteasy.jackson2.provider)
                api(libs.resteasy.core)
                implementation(libs.guice)
            }   
        """.trimIndent())
        p.file("module/src/main/java/foo/Bar.java", """
            package foo;
            public class Bar {
                private org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap c;
            }
        """.trimIndent())

        val result = p.failQualityGate()

        assertThat(result.output).contains("""
            Unused dependencies which should be removed:
              implementation(libs.guice)
            
            Existing dependencies which should be modified to be as indicated:
              implementation(libs.resteasy.core) (was api)
            
            Dependencies which should be removed or changed to runtime-only:
              runtimeOnly(libs.resteasy.jackson2.provider) (was implementation)
        """.trimIndent())
    }

    companion object {
        @JvmStatic
        fun pluginIds(): Array<String> {
            val pluginList = File("src/main/kotlin").listFiles()!!.filter { it.isFile }.map {
                it.name.substringBeforeLast(".gradle.kts")
            }
            return pluginList.toTypedArray()
        }
    }
}
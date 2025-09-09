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
    fun `qualityGate fails for wrong dependency scopes`() {
        val p = GradleProject().withMinimalStructure()
        p.catalog("""
            [libraries]
            guava = { module = "com.google.guava:guava", version = "30.1-jre" }
            jackson-core = { module = "com.fasterxml.jackson.core:jackson-databind", version = "2.13.5" }
        """.trimIndent())
        p.moduleBuildFile("""
            plugins {
                id("org.example.gradle.component.library")
            }  
        """.trimIndent())
        p.file("module/src/main/java/module-info.java", """
            module foo.module {
                requires com.google.common;
                requires transitive com.fasterxml.jackson.databind;
            }
        """.trimIndent())
        p.file("module/src/main/java/foo/Bar.java", """
            package foo;
            public class Bar {
                private com.fasterxml.jackson.databind.ObjectMapper om;
            }
        """.trimIndent())

        val result = p.failQualityGate()

        assertThat(result.output).contains("""
          Please add the following requires directives:
              requires com.fasterxml.jackson.databind;
          
          Please remove the following requires directives (or change to runtimeOnly):
              requires com.google.common;
              requires transitive com.fasterxml.jackson.databind;
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
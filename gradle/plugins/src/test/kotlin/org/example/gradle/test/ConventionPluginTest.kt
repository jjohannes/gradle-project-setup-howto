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
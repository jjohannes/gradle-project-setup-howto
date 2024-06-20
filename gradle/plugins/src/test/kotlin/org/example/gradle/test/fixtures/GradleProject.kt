package org.example.gradle.test.fixtures

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import java.io.File
import java.lang.management.ManagementFactory
import java.nio.file.Files

/**
 * Access to a minimal project inside a temporary folder.
 * The project contain files that are expected to exist in our setup.
 */
class GradleProject {

    private val projectDir = Files.createTempDirectory("gradle-build").toFile()
    private val gradlePropertiesFile = file("gradle.properties")
    private val settingsFile = file("settings.gradle.kts")
    private val rootBuildFile = file("build.gradle.kts")
    private val catalog = file("gradle/libs.versions.toml")
    private val versions = file("gradle/versions/build.gradle.kts")
    private val aggregation = file("gradle/aggregation/build.gradle.kts")
    private val appBuildFile = file("app/build.gradle.kts")
    private val moduleBuildFile = file("module/build.gradle.kts")
    private val jdkVersionFile = file("gradle/jdk-version.txt")

    fun withMinimalStructure(): GradleProject {
        gradlePropertiesFile.writeText("""
            org.gradle.configuration-cache=true
            dependency.analysis.autoapply=false
            # org.gradle.unsafe.isolated-projects=true
            org.gradle.caching=true
            kotlin.stdlib.default.dependency=false
        """.trimIndent())
        settingsFile.writeText("""
            plugins {
                id("org.example.gradle.feature.repositories")
                id("org.example.gradle.feature.project-structure")
            }
            rootProject.name = "test-project"
        """.trimIndent())
        rootBuildFile.writeText("""
            plugins {
                id("org.example.gradle.check.dependencies.root")
            }
        """.trimIndent())
        versions.writeText("""plugins {
            id("org.example.gradle.base.lifecycle")
            id("org.example.gradle.feature.use-all-catalog-versions")
        }""".trimIndent())
        aggregation.writeText("")
        catalog("""
            [libraries]
            foo = { module = "foo:bar" }
        """.trimIndent())
        jdkVersionFile.writeText("21")
        appBuildFile.writeText("""plugins { id("org.example.gradle.component.application") }""")
        file("app/src/main/resources").mkdirs()
        return this
    }

    fun settingsFile(content: String) = settingsFile.also {
        it.writeText(content)
    }


    fun rootBuildFile(content: String) = rootBuildFile.also {
        it.writeText(content)
    }

    fun moduleBuildFile(content: String) = moduleBuildFile.also {
        it.writeText(content)
    }

    fun catalog(content: String) = catalog.also {
        it.writeText(content)
    }

    fun file(path: String, content: String? = null) = File(projectDir, path).also {
        it.parentFile.mkdirs()
        if (content != null) {
            it.writeText(content)
        }
    }

    fun help(): BuildResult = runner(listOf("help")).build()

    fun build(): BuildResult = runner(listOf("build")).build()

    fun qualityGate(): BuildResult = runner(listOf("qualityGate")).build()

    fun failQualityGate(): BuildResult = runner(listOf("qualityGate")).buildAndFail()

    private fun runner(args: List<String>) = GradleRunner.create()
        .forwardOutput()
        .withPluginClasspath()
        .withProjectDir(projectDir)
        .withArguments(args + listOf("-s", "--warning-mode=all"))
        .withDebug(ManagementFactory.getRuntimeMXBean().inputArguments.toString().contains("-agentlib:jdwp"))

}
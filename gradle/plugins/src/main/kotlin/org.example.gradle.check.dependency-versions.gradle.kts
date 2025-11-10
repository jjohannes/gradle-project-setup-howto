import org.example.gradle.tasks.DependencyVersionUpgradesCheck
import org.example.gradle.tasks.JavaVersionConsistencyCheck

plugins {
    id("org.example.gradle.base.lifecycle")
    id("java-platform")
}

tasks.register<JavaVersionConsistencyCheck>("checkVersionConsistency") {
    group = JavaBasePlugin.VERIFICATION_GROUP
    definedVersions = provider {
        configurations["api"].dependencyConstraints.associate { "${it.group}:${it.name}" to it.version!! }
    }
    aggregatedClasspath = provider { configurations["mainRuntimeClasspath"].incoming.resolutionResult.allComponents }
    reportFile = layout.buildDirectory.file("reports/version-consistency.txt")
}

tasks.named("qualityCheck") { dependsOn(tasks.named("checkVersionConsistency")) }

tasks.named("qualityGate") { dependsOn(tasks.named("checkVersionConsistency")) }

val latestReleases =
    configurations.dependencyScope("dependencyVersionUpgrades") {
        withDependencies {
            add(project.dependencies.platform(project(project.path)))
            configurations.api.get().dependencies.forEach {
                add(project.dependencies.platform("${it.group}:${it.name}:latest.release") { isTransitive = false })
            }
            configurations.api.get().dependencyConstraints.forEach {
                add(project.dependencies.create("${it.group}:${it.name}:latest.release") { isTransitive = false })
            }
        }
    }
val latestReleasesPath =
    configurations.resolvable("latestReleasesPath") {
        attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
        attributes.attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
        extendsFrom(latestReleases.get())
    }

tasks.register<DependencyVersionUpgradesCheck>("checkForDependencyVersionUpgrades") {
    group = HelpTasksPlugin.HELP_GROUP
    projectName.set(project.name)
    dependencies.set(configurations.api.get().dependencies.map { "${it.group}:${it.name}:${it.version}" })
    dependencyConstraints.set(
        configurations.api.get().dependencyConstraints.map { "${it.group}:${it.name}:${it.version}" }
    )
    latestReleasesResolutionResult.set(latestReleasesPath.map { it.incoming.resolutionResult.allComponents })
}

import org.example.gradle.tasks.JavaVersionConsistencyCheck

plugins { id("java-platform") }

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

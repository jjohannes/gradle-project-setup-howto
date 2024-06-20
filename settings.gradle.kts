pluginManagement {
    includeBuild("gradle/plugins")
}
plugins {
    id("org.example.gradle.report.develocity")
    id("org.example.gradle.feature.build-cache")
    id("org.example.gradle.feature.project-structure")
    id("org.example.gradle.feature.repositories")
}

rootProject.name = "gradle-project-setup-howto"

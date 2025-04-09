pluginManagement {
    includeBuild("gradle/plugins")
}
plugins {
    id("org.example.gradle.report.develocity")
    id("org.example.gradle.feature.repositories")
    id("org.example.gradle.feature.build-cache")
    id("org.example.gradle.feature.project-structure")
}
gradle.lifecycle.beforeProject {
    if (this.path == ":") {
        apply(plugin = "org.example.gradle.base.lifecycle")
        apply(plugin = "org.example.gradle.check.format-gradle.root")
    }
}

rootProject.name = "gradle-project-setup-howto"

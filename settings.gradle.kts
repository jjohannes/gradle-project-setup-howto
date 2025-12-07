pluginManagement { includeBuild("gradle/plugins") }

plugins { id("org.example.gradle.build") }

rootProject.name = "gradle-project-setup-howto"

javaModules {
    directory("engine") {
        group = "org.example"
        plugin("org.example.gradle.component.library")
        plugin("org.example.gradle.feature.publish")
    }
    directory("renderer") {
        group = "org.example"
        plugin("org.example.gradle.component.library")
    }
    directory("jamcatch") {
        group = "org.example"
        plugin("org.example.gradle.component.library")
        plugin("org.example.gradle.feature.test-end-to-end")
    }
    directory("apps") {
        module("app-jamcatch")
        module("app-jamcatch-debug")
    }
}

pluginManagement {
    includeBuild("gradle/plugins")
    repositories.gradlePluginPortal()
    repositories.google()
}

plugins { id("org.example.gradle.build") }

rootProject.name = "gradle-project-setup-howto"

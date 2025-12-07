pluginManagement {
    repositories.gradlePluginPortal()
    repositories.google()
    includeBuild("gradle/plugins")
}

plugins { id("org.example.gradle.build") }

rootProject.name = "gradle-project-setup-howto"

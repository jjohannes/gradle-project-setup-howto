pluginManagement {
    includeBuild("../meta-plugins")
}
plugins {
    id("org.example.settings")
}

dependencyResolutionManagement {
    repositories.gradlePluginPortal()
    includeBuild("../meta-plugins") // for 'build-parameters'
}

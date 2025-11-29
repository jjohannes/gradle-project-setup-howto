import org.gradlex.javamodule.dependencies.initialization.RootPluginsExtension

plugins {
    id("org.example.gradle.report.develocity")
    id("org.example.gradle.feature.repositories")
    id("org.example.gradle.feature.build-cache")
    id("org.example.gradle.feature.project-structure")
}

configure<RootPluginsExtension> {
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.check.format-base")
    id("org.example.gradle.check.format-gradle.root")
}

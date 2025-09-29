plugins {
    id("org.gradle.java")
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.report.code-coverage")
    id("org.example.gradle.report.plugin-analysis")
    id("org.example.gradle.report.test")
}

dependencies { implementation(projects.app) }

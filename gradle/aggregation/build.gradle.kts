plugins {
    id("org.gradle.java")
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.check.format-gradle")
    id("org.example.gradle.report.code-coverage")
    id("org.example.gradle.report.plugin-analysis")
    id("org.example.gradle.report.sbom")
    id("org.example.gradle.report.test")
}

dependencies { implementation(projects.app) }

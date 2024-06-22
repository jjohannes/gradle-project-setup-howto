plugins {
    id("org.example.gradle.report.code-coverage")
    id("org.example.gradle.report.plugin-analysis")
    id("org.example.gradle.report.sbom")
    id("org.example.gradle.report.test")
}

dependencies {
    implementation(projects.app)
}

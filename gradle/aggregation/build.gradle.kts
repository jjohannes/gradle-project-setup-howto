plugins {
    id("org.example.gradle.report.code-coverage")
    id("org.example.gradle.report.plugin-analysis")
    id("org.example.gradle.report.sbom")
}

dependencies {
    implementation(projects.app)
}

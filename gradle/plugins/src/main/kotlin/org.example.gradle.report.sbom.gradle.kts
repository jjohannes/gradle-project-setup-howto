plugins {
    id("org.gradle.java")
    id("org.cyclonedx.bom")
}

// Generate a Software Bill of Materials for the software product
tasks.cyclonedxBom {
    includeConfigs.add(configurations.runtimeClasspath.name)
    destination = layout.buildDirectory.dir("reports/sbom").get().asFile
}

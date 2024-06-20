plugins {
    id("org.gradle.java")
    id("org.cyclonedx.bom")
}

// Generate a Software Bill of Materials for the software product
tasks.cyclonedxBom {
    notCompatibleWithConfigurationCache("https://github.com/CycloneDX/cyclonedx-gradle-plugin/issues/193")
    includeConfigs.add(configurations.runtimeClasspath.name)
    destination = layout.buildDirectory.dir("reports/sbom").get().asFile
}

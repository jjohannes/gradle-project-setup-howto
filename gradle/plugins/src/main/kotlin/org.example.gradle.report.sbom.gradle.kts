plugins {
    id("org.gradle.java")
    id("org.cyclonedx.bom")
}

// Generate a Software Bill of Materials for the software product
tasks.cyclonedxBom {
    includeConfigs.add(configurations.runtimeClasspath.name)
    jsonOutput = layout.buildDirectory.file("reports/sbom/bom.json")
    xmlOutput = layout.buildDirectory.file("reports/sbom/bom.xml")
}

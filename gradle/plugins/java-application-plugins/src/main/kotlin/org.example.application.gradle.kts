import org.example.application.tasks.Jpackage
import org.example.application.tasks.MD5DirectoryChecksum
import org.example.application.tasks.VersionXml

plugins {
    id("org.example.java")
    id("application")               // For stand-alone application packaging
    id("jacoco-report-aggregation") // get and aggregated coverage report for all tests
    id("test-report-aggregation")   // get and aggregated result report for all tests
    id("org.example.war")           // For web application packaging/deployment
    id("org.example.end2end-testing")
    id("io.fuchs.gradle.classpath-collision-detector")
    id("org.owasp.dependencycheck")
}

configurations.aggregateTestReportResults {
    shouldResolveConsistentlyWith(configurations.appRuntimeClasspath.get())
}
configurations.aggregateCodeCoverageReportResults {
    shouldResolveConsistentlyWith(configurations.appRuntimeClasspath.get())
}

// Generate additional resources required at application runtime
val generateVersionXml = tasks.register<VersionXml>("generateVersionXml") {
    mainVersion.set(providers.fileContents(
        rootProject.layout.projectDirectory.file("gradle/version.txt")).asText)
    xmlFile.set(layout.buildDirectory.file("generated-resources/xml/version.xml"))
}
val resourcesChecksum = tasks.register<MD5DirectoryChecksum>("resourcesChecksum") {
    inputDirectory.set(layout.projectDirectory.dir("src/main/resources"))
    checksumFile.set(layout.buildDirectory.file("generated-resources/md5/resources.MD5"))
}

tasks.processResources {
    from(generateVersionXml)
    from(resourcesChecksum)
}

tasks.register<Jpackage>("jpackage") {
    group = "build"

    javaInstallation.set(javaToolchains.compilerFor(java.toolchain).map { it.metadata })
    operatingSystem.set("macos")
    architecture.set("aarch64")

    mainModule.set(application.mainModule)
    mainClass.set(application.mainClass)
    version.set(project.version as String)
    resources.set(layout.projectDirectory.dir("resources"))
    applicationName.set("Gradle Project Setup Howto")
    applicationDescription.set("How to structure a growing Gradle project with smart dependency management?")
    vendor.set("Jendrik Johannes (Onepiece.Software)")
    copyright.set("Copyright © 2023 Jendrik Johannes")

    modulePath.from(tasks.jar)
    modulePath.from(configurations.runtimeClasspath)

    destination.set(layout.buildDirectory.dir("package"))
}

dependencyCheck {
    scanConfigurations = listOf(configurations.runtimeClasspath.get().name)
    autoUpdate = false
}

tasks.check {
    dependsOn(tasks.testAggregateTestReport)
    dependsOn(tasks.testCodeCoverageReport)
    dependsOn(tasks.detectCollisions)
}

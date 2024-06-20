plugins {
    id("org.gradle.java")
    id("org.gradle.maven-publish")
}

// Set the version from 'version.txt'
version = providers.fileContents(isolated.rootProject.projectDirectory.file("gradle/version.txt")).asText.getOrElse("")

// On CI, add timestamp for publishing
if (providers.environmentVariable("CI").getOrElse("false").toBoolean()) {
    val gitCommitTimestamp =
        providers
            .exec { commandLine("git", "log", "-1", "--format=%ad", "--date=format:%Y%m%d%H%M%S") }
            .standardOutput
            .asText
            .get()
            .trim()
    version = "$version-$gitCommitTimestamp"
}

// Publish with sources and Javadoc
java {
    withSourcesJar()
    withJavadocJar()
}

tasks.named("sourcesJar") { group = null }

tasks.named("javadocJar") { group = null }

publishing.publications.create<MavenPublication>("mavenJava") {
    from(components["java"])

    // We use consistent resolution + a platform for controlling versions
    // -> Publish the versions that are the result of the consistent resolution
    versionMapping { allVariants { fromResolutionResult() } }
}

// The repository to publish to
publishing.repositories {
    maven(providers.environmentVariable("PUBLISHING_REPOSITORY_URL").getOrElse("/tmp/repo")) {
        if (url.scheme == "https") {
            credentials {
                username = providers.environmentVariable("PUBLISHING_REPOSITORY_USER").get()
                password = providers.environmentVariable("PUBLISHING_REPOSITORY_PWD").get()
            }
        }
    }
}

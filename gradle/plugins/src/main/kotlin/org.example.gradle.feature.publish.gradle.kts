plugins {
    id("org.gradle.java")
    id("org.gradle.maven-publish")
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

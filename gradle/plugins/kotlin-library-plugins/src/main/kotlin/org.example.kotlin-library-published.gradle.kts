plugins {
    id("maven-publish")
    id("org.example.kotlin-library")
}

// Publish with sources and Javadoc
java {
    withSourcesJar()
    withJavadocJar()
}

// Configure details of Javadoc generation
tasks.javadoc {
    (options as StandardJavadocDocletOptions).apply {
        memberLevel = JavadocMemberLevel.PUBLIC
        isAuthor = true
    }
}

publishing.publications.create<MavenPublication>("mavenJava") {
    from(components["java"])

    // We use consistent resolution + a platform for controlling versions
    // -> Publish the versions that are the result of the consistent resolution
    versionMapping {
        allVariants {
            fromResolutionResult()
        }
    }
}

// The repository to publish to
publishing.repositories {
    maven("/tmp/my-repo")
}

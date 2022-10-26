plugins {
    id("base")
    id("org.example.dependency-rules")
}

// Set the group (some components will be published)
group = "org.example.product"

// Set the version from 'version.txt'
version = providers.fileContents(
    rootProject.layout.projectDirectory.file("gradle/version.txt")).asText.getOrElse("")

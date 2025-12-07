plugins { id("org.gradle.base") }

// Set the version from 'version.txt'
@Suppress("UnstableApiUsage")
version = providers.fileContents(isolated.rootProject.projectDirectory.file("gradle/version.txt")).asText.getOrElse("")

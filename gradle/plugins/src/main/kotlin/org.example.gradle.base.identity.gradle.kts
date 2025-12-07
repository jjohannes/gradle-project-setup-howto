plugins { id("org.gradle.base") }

// Set the version from 'version.txt'
version = providers.fileContents(layout.settingsDirectory.file("gradle/version.txt")).asText.getOrElse("")

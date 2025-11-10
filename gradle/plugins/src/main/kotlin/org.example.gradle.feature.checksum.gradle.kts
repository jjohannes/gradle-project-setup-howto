import org.example.gradle.tasks.MD5DirectoryChecksum

plugins { id("org.gradle.java") }

// Generate additional resources required at application runtime
// This is an example for creating and integrating a custom task implementation.
tasks.register<MD5DirectoryChecksum>("resourcesChecksum") {
    val resources = layout.projectDirectory.dir("src/main/resources")
    enabled = resources.asFile.exists() // the task is optional, only active when the directory exists

    inputDirectory = resources
    checksumFile = layout.buildDirectory.file("generated-resources/md5/resources.MD5")
}

tasks.processResources { into("META-INF") { from(tasks.named("resourcesChecksum")) } }

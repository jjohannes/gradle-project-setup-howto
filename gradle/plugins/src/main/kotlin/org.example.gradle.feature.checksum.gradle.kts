import org.example.gradle.tasks.MD5DirectoryChecksum

// Generate additional resources required at application runtime
// This is an example for creating and integrating a custom task implementation.
tasks.register<MD5DirectoryChecksum>("resourcesChecksum") {
    inputDirectory = layout.projectDirectory.dir("src/main/resources")
    checksumFile = layout.buildDirectory.file("generated-resources/md5/resources.MD5")
}

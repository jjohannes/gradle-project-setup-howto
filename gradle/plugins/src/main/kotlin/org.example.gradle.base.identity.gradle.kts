plugins { id("org.gradle.base") }

// Set the group required to refer to a Module "from outside".
// I.e., when it is published or used in Included Builds.
group = "org.example.product.kotlin"

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

plugins { id("org.gradle.java") }

// end-to-end tests
@Suppress("UnstableApiUsage")
testing.suites.register<JvmTestSuite>("testEndToEnd") {
    targets.configureEach {
        testTask {
            // testing needs to be performed with a renderer implementation that uses this env setting.
            environment("PRESENTATION_FOLDER", project.layout.buildDirectory.dir("test-screenshot").get().asFile)
            // disable on CI for now, as the tests require a display
            if (providers.environmentVariable("CI").getOrElse("false").toBoolean()) {
                enabled = false
            }
        }
    }
    targets.named("testEndToEnd") {
        testTask {
            group = "build"
            options {
                this as JUnitPlatformOptions
                excludeTags("slow")
            }
        }
    }
    // Add a second task for the endToEndTest suite
    targets.register("testEndToEndSlow") {
        testTask {
            group = "build"
            options {
                this as JUnitPlatformOptions
                includeTags("slow")
            }
        }
    }
}

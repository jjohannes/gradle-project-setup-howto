plugins { id("org.gradle.java") }

// Specific API fixtures used for testing without live service
val mockApi = sourceSets.create("mockApi")

java.registerFeature(mockApi.name) { usingSourceSet(mockApi) }

tasks.named("mockApiJar") { group = null }

// end-to-end tests
testing.suites.create<JvmTestSuite>("testEndToEnd") {
    testType = TestSuiteType.FUNCTIONAL_TEST
    targets.named("testEndToEnd") {
        testTask {
            group = "build"
            options {
                this as JUnitPlatformOptions
                excludeTags("slow")
            }
        }
        tasks.check { dependsOn(testTask) }
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

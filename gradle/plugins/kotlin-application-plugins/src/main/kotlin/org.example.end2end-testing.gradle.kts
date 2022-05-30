plugins {
    id("org.example.kotlin")
}

// Specific API fixtures used for testing without live service
val mockApi = sourceSets.create("mockApi")
java.registerFeature(mockApi.name) {
    usingSourceSet(mockApi)
}

// end-to-end tests located in the :app project
testing.suites.create<JvmTestSuite>("endToEndTest") {
    useJUnitJupiter("")
    targets.all {
        testTask {
            options {
                this as JUnitPlatformOptions
                excludeTags("slow")
            }
        }
        tasks.check {
            dependsOn(testTask)
        }
    }
}

// Add a second task for the endToEndTest suite (not yet supported by suites directly)
tasks.register<Test>("endToEndTestSlow") {
    testClassesDirs = sourceSets["endToEndTest"].output.classesDirs
    classpath = sourceSets["endToEndTest"].runtimeClasspath
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    useJUnitPlatform { includeTags("slow") }
}

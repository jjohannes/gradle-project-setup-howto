plugins {
    id("org.gradle.jacoco") // Record test coverage data during test execution
    id("org.gradle.java")
    id("org.gradlex.java-module-testing")
}

testing.suites.named<JvmTestSuite>("test") {
    targets.all {
        useJUnitJupiter() // Use JUnit 5 as test framework
        // Configure details for test executions directly on 'Test' task
        testTask {
            group = "build"

            maxParallelForks = 4

            testLogging.showStandardStreams = true

            maxHeapSize = "1g"
            systemProperty("file.encoding", "UTF-8")
        }
    }
}

testing.suites.withType<JvmTestSuite> {
    // remove automatically added compile time dependencies, as we define them explicitly
    configurations.getByName(sources.implementationConfigurationName) {
        withDependencies { removeIf { it.group == "org.junit.jupiter" && it.name == "junit-jupiter" } }
    }
    // Configure common test runtime dependencies for *all* projects
    dependencies {
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine")
        runtimeOnly("org.slf4j:slf4j-simple")
    }
}

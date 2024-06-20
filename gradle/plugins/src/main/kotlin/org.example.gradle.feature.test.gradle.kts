plugins {
    id("org.gradle.jacoco") // Record test coverage data during test execution
    id("org.gradle.java")
}

// Configure details for *all* test executions directly on 'Test' task
tasks.test {
    group = "build"

    useJUnitPlatform() // Use JUnit 5 as test framework
    maxParallelForks = 4

    testLogging.showStandardStreams = true

    maxHeapSize = "1g"
    systemProperty("file.encoding", "UTF-8")
}

// Configure common test runtime dependencies for *all* projects
dependencies {
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.slf4j:slf4j-simple")
}

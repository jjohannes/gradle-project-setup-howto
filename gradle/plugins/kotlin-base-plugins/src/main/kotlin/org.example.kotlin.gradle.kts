plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm")
    id("jacoco") // Record test coverage data during test execution
    id("org.example.base")
    id("org.example.consistent-resolution")
    id("org.example.dependency-analysis-project")
}

// Configure Java/Kotlin compilation on java/kotlin {} extension or directly on 'JavaCompile' tasks
val javaLanguageVersion = JavaLanguageVersion.of(17)
java {
    toolchain.languageVersion.set(javaLanguageVersion)
}
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}
kotlin {
    jvmToolchain {
        languageVersion.set(javaLanguageVersion)
    }
}

// Configure details for *all* test executions directly on 'Test' task
tasks.withType<Test>().configureEach {
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

// Add a 'compileAll' task to run all of Java compilation in one go
tasks.register("compileAll") {
    group = LifecycleBasePlugin.BUILD_GROUP
    description = "Compile all Java code (use to prime the build cache for CI pipeline)"
    dependsOn(tasks.withType<JavaCompile>())
}

import org.gradlex.javamodule.dependencies.dsl.GradleOnlyDirectives

plugins {
    id("java")
    id("jacoco") // Record test coverage data during test execution
    id("org.example.base")
    id("org.example.consistent-resolution")
}

// Configure Java compilation on java {} extension or directly on 'JavaCompile' tasks
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
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
extensions.configure<GradleOnlyDirectives>("testModuleInfo") {
    runtimeOnly("org.junit.jupiter.engine")
    runtimeOnly("org.slf4j.simple")
}

// We use the merge Jar feature of 'extra-java-module-info' and javaModulesMergeJars also needs to get version from somewhere
configurations.javaModulesMergeJars.get().shouldResolveConsistentlyWith(configurations["appRuntimeClasspath"])

// Add a 'compileAll' task to run all of Java compilation in one go
tasks.register("compileAll") {
    group = LifecycleBasePlugin.BUILD_GROUP
    description = "Compile all Java code (use to prime the build cache for CI pipeline)"
    dependsOn(tasks.withType<JavaCompile>())
}

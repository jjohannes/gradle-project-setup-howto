plugins {
    id("org.gradle.java")
    id("org.example.gradle.base.lifecycle")
}

// Configure which JDK and Java version to build with. The version is defined in
// 'gradle/jdk-version.txt' so that GitHub actions can also pick it up from there.
java {
    toolchain.languageVersion =
        JavaLanguageVersion.of(
            providers
                .fileContents(isolated.rootProject.projectDirectory.file("gradle/jdk-version.txt"))
                .asText
                .get()
                .trim()
        )
}

// Configuration to make the build reproducible. This means we override settings that are, by
// default, platform dependent (e.g. different default encoding on Windows and Unix systems).
tasks.withType<JavaCompile>().configureEach {
    options.apply {
        isFork = true
        encoding = "UTF-8"
        compilerArgs.add("-implicit:none")
        compilerArgs.add("-Werror")
        compilerArgs.add("-Xlint:all,-serial,-exports")
    }
}

tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
    filePermissions { unix("0664") }
    dirPermissions { unix("0775") }
}

// Tweak 'lifecycle tasks': These are the tasks in the 'build' group that are used in daily
// development. Under normal circumstances, these should be all the tasks developers needs
// in their daily work.
tasks.named("qualityCheck") { dependsOn(tasks.withType<JavaCompile>()) }

tasks.named("qualityGate") { dependsOn(tasks.withType<JavaCompile>()) }

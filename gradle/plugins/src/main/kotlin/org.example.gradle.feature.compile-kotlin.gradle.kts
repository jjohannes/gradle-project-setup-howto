import org.gradle.jvm.toolchain.JavaLanguageVersion

plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.example.gradle.base.lifecycle")
}

kotlin {
    jvmToolchain {
        languageVersion =
            JavaLanguageVersion.of(
                providers
                    .fileContents(isolated.rootProject.projectDirectory.file("gradle/jdk-version.txt"))
                    .asText
                    .get()
                    .trim()
            )
    }
}

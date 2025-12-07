plugins {
    id("org.gradle.application")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.module-system-rules")
    id("org.example.gradle.base.identity")
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.check.dependencies")
    id("org.example.gradle.check.format-base")
    id("org.example.gradle.check.format-gradle")
    id("org.example.gradle.check.format-java")
    id("org.example.gradle.feature.compile-java")
    id("org.example.gradle.feature.javadoc")
    id("org.example.gradle.feature.jpackage")
}

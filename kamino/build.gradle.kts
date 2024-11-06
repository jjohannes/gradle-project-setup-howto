plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(projects.coruscant)
    api(libs.kotlin.stdlib)

    testImplementation(libs.junit.jupiter.api)
}

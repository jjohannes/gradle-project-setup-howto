plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(projects.coruscant)

    testImplementation(libs.junit.jupiter.api)
}

plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(projects.coruscant)
    api(libs.kotlin.stdlib)
    api(libs.resteasy.core)
    implementation(libs.resteasy.guice)
    implementation(libs.resteasy.jackson2.provider)

    testImplementation(libs.junit.jupiter.api)
}

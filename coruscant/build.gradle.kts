plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(libs.jackson.annotations)
    api(libs.kotlin.stdlib)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.jackson.core)
    runtimeOnly(libs.jackson.databind)
    runtimeOnly(libs.jackson.datatype.json.org)
    runtimeOnly(libs.jackson.datatype.jsr310)
    runtimeOnly(libs.jakarta.mail.impl)

    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter.api)
}

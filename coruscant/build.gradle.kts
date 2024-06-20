plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(libs.guava)
    api(libs.jackson.annotations)
    api(libs.jackson.databind)
    api(libs.jakarta.inject.api)
    api(libs.opensaml)
    api(libs.org.json)
    implementation(libs.httpcomponents.fluent.hc)
    implementation(libs.jackson.core)
    implementation(libs.jackson.datatype.json.org)
    implementation(libs.jackson.datatype.jsr310)
    implementation(libs.jakarta.activation)
    implementation(libs.jakarta.mail.impl)
    implementation(libs.org.reflections)
    implementation(libs.slf4j.api)

    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter.api)
}

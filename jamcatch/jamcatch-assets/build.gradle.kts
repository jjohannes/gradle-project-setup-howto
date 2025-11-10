plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.test-end-to-end")
}

dependencies {
    api(projects.javarcaModel)
    implementation(libs.commons.io)

    testImplementation(libs.junit.api)

    testEndToEndImplementation(testFixtures(projects.javarcaEngine))
    testEndToEndImplementation(libs.junit.api)
}

plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.compile-kotlin")
    id("org.example.gradle.feature.test-end-to-end")
}

dependencies {
    api(projects.javarcaModel)
    implementation(libs.slf4j.api)

    testImplementation(libs.junit.api)

    testEndToEndApi(libs.junit.api)
    testEndToEndImplementation(testFixtures(projects.javarcaEngine))
}

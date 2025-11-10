plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.test-fixtures")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(projects.javarcaModel)
    implementation(libs.slf4j.api)

    testImplementation(libs.junit.api)

    testFixturesImplementation(libs.slf4j.api)
    testFixturesRuntimeOnly(projects.rendererLwjgl)
}

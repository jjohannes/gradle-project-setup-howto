plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.test-end-to-end")
}

dependencies {
    api(projects.javarcaModel)
    implementation("commons-io:commons-io")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testEndToEndImplementation(testFixtures(projects.javarcaEngine))
    testEndToEndImplementation("org.junit.jupiter:junit-jupiter-api")
}

plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.test-fixtures")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(projects.javarcaModel)
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testFixturesImplementation("org.slf4j:slf4j-api")
    testFixturesRuntimeOnly(projects.rendererLwjgl)
}

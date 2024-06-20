plugins { id("org.example.gradle.component.library") }

dependencies {
    api(projects.naboo)
    api(projects.tatooine)
    implementation(projects.bespin)
    implementation(projects.kamino)

    testImplementation(libs.junit.jupiter.api)
}

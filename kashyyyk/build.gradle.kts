plugins { id("org.example.gradle.component.library") }

dependencies {
    api(projects.naboo)
    api(projects.tatooine)
    api(libs.kotlin.stdlib)
    implementation(projects.bespin)
    implementation(projects.kamino)

    testImplementation(libs.junit.jupiter.api)
}

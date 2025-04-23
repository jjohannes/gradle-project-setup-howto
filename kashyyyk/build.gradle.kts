plugins { id("org.example.gradle.component.library") }

dependencies {
    api(projects.naboo)
    api(projects.tatooine)
    implementation(projects.bespin)
    implementation(projects.kamino)

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

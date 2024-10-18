plugins { id("org.example.gradle.component.library") }

dependencies {
    api(projects.corellia)
    implementation(projects.coruscant)
    implementation(libs.velocity.engine.core)

    testImplementation(testFixtures(projects.corellia))
    testImplementation(libs.junit.jupiter.api)
}

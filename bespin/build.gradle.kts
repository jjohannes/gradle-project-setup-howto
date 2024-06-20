plugins { id("org.example.gradle.component.library") }

// For isolated-projects, use 'requireCapability' notation instead of testFixtures(...)
dependencies {
    api(projects.corellia)
    implementation(projects.coruscant)
    implementation(libs.velocity.engine.core)

    testImplementation(projects.corellia) { capabilities { requireCapability("${project.group}:$name-test-fixtures") } }
    testImplementation(libs.junit.jupiter.api)
}

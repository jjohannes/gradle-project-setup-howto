plugins { id("org.example.gradle.component.library") }

dependencies {
    api(projects.corellia)
    implementation(projects.coruscant)
    implementation("org.apache.velocity:velocity-engine-core")

    testImplementation(projects.corellia) { capabilities { requireFeature("test") } }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

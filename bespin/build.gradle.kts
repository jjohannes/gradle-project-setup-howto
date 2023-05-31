plugins {
    id("org.example.java-library")
}

dependencies {
    api(project(":corellia"))

    implementation(project(":coruscant"))
    implementation("org.apache.velocity:velocity-engine-core")

    testImplementation(testFixtures(project(":corellia")))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

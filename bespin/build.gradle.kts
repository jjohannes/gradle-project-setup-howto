plugins {
    id("org.example.kotlin-library")
}

dependencies {
    api(project(":corellia"))
    api("org.jetbrains.kotlin:kotlin-stdlib")

    implementation(project(":coruscant"))
    implementation("org.apache.velocity:velocity-engine-core")

    testImplementation(testFixtures(project(":corellia")))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

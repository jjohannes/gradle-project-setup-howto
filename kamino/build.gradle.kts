plugins {
    id("org.example.kotlin-library-published")
}

dependencies {
    api(project(":coruscant"))
    api("org.jboss.resteasy:resteasy-core")
    api("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.jboss.resteasy:resteasy-guice")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

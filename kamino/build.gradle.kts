plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.publish")
}

dependencies {
    api(projects.coruscant)
    api("org.jboss.resteasy:resteasy-core")
    implementation("org.jboss.resteasy:resteasy-guice")
    implementation("org.jboss.resteasy:resteasy-jackson2-provider")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

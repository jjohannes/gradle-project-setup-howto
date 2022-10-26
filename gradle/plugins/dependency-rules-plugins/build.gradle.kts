plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation("dev.jacomet.gradle.plugins:logging-capabilities")
    implementation("org.gradlex:java-ecosystem-capabilities")
}

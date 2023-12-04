plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation("dev.jacomet.gradle.plugins:logging-capabilities")
    implementation("org.gradlex:extra-java-module-info")
    implementation("org.gradlex:java-ecosystem-capabilities")
    implementation("org.gradlex:java-module-dependencies")
}

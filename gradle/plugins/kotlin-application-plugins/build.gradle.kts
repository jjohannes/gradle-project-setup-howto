plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation(project(":kotlin-base-plugins"))
    implementation("org.example:build-parameters-plugins")
    implementation("org.owasp:dependency-check-gradle")
    implementation("io.fuchs.gradle.classpath-collision-detector:classpath-collision-detector")
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation("com.autonomousapps:dependency-analysis-gradle-plugin")
    implementation("org.gradlex:java-module-dependencies")
}

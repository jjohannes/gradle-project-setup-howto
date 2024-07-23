plugins { `kotlin-dsl` }

repositories { gradlePluginPortal() }

dependencies {
    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:1.32.0")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.25.0")
    implementation("com.gradle:develocity-gradle-plugin:3.17.6")
    implementation("io.fuchs.gradle.classpath-collision-detector:classpath-collision-detector:1.0.0")
    implementation("org.cyclonedx:cyclonedx-gradle-plugin:1.8.2")
    implementation("org.gradlex:jvm-dependency-conflict-resolution:2.1.2")
}

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    dependencies {
        implementation("org.junit.jupiter:junit-jupiter-params")
        implementation("org.assertj:assertj-core:3.26.3")
    }
}

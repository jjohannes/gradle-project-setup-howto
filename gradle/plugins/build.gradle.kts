plugins { `kotlin-dsl` }

repositories { gradlePluginPortal() }

dependencies {
    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:2.19.0")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.2.1")
    implementation("com.gradle:develocity-gradle-plugin:4.1")
    implementation("io.fuchs.gradle.classpath-collision-detector:classpath-collision-detector:1.0.0")
    implementation("org.cyclonedx:cyclonedx-gradle-plugin:2.3.1")
    implementation("org.gradlex:jvm-dependency-conflict-resolution:2.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.0")
}

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    dependencies {
        implementation("org.junit.jupiter:junit-jupiter-params")
        implementation("org.assertj:assertj-core:3.27.3")
    }
}

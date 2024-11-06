plugins { `kotlin-dsl` }

repositories { gradlePluginPortal() }

dependencies {
    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:2.13.3")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.25.0")
    implementation("com.gradle:develocity-gradle-plugin:3.19.2")
    implementation("io.fuchs.gradle.classpath-collision-detector:classpath-collision-detector:1.0.0")
    implementation("org.cyclonedx:cyclonedx-gradle-plugin:2.2.0")
    implementation("org.gradlex:extra-java-module-info:1.9")
    implementation("org.gradlex:java-module-dependencies:1.7")
    implementation("org.gradlex:java-module-packaging:0.1")
    implementation("org.gradlex:java-module-testing:1.4")
    implementation("org.gradlex:jvm-dependency-conflict-resolution:2.1.2")
}

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    dependencies {
        implementation("org.junit.jupiter:junit-jupiter-params")
        implementation("org.assertj:assertj-core:3.27.3")
    }
}

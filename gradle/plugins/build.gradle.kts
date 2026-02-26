plugins { `kotlin-dsl` }

repositories { gradlePluginPortal() }

dependencies {
    implementation("com.autonomousapps:dependency-analysis-gradle-plugin:3.6.1")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:8.2.1")
    implementation("com.gradle:develocity-gradle-plugin:4.3.2")
    implementation("io.fuchs.gradle.classpath-collision-detector:classpath-collision-detector:1.0.0")
    implementation("io.mvnpm.gradle.plugin:native-java-plugin:1.0.0")
    implementation("org.cyclonedx:cyclonedx-gradle-plugin:2.4.1")
    implementation("org.gradlex:jvm-dependency-conflict-resolution:2.5")
}

testing.suites.named<JvmTestSuite>("test") {
    useJUnitJupiter()
    dependencies {
        implementation("org.junit.jupiter:junit-jupiter-params")
        implementation("org.assertj:assertj-core:3.27.7")
    }
}

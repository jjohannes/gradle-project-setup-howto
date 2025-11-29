plugins {
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.feature.compile-java")
    id("com.android.library")
}

android {
    compileSdk = 36
    namespace = "org.example.${project.name.replace("-", ".")}"

    defaultConfig { minSdk = 36 }
}

// Configure common test runtime dependencies for android
dependencies {
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Android specific tests also use JUnit Jupiter
tasks.withType<Test>().configureEach { useJUnitPlatform() }

// Required, because 'org.gradlex.jvm-dependency-conflict-resolution' does not
// automatically configure everything for Android (yet)
android {
    libraryVariants.all {
        compileConfiguration.configureConsistentResolution()
        runtimeConfiguration.configureConsistentResolution()
        unitTestVariant.compileConfiguration.configureConsistentResolution()
        unitTestVariant.runtimeConfiguration.configureConsistentResolution()
        if (testVariant != null) {
            testVariant.compileConfiguration.configureConsistentResolution()
            testVariant.runtimeConfiguration.configureConsistentResolution()
        }
    }
}

fun Configuration.configureConsistentResolution() {
    extendsFrom(configurations["internal"])
    shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"])
}

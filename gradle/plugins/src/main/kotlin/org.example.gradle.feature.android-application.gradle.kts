plugins {
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.feature.compile-java")
    id("com.android.application")
}

val versionString =
    providers.fileContents(isolated.rootProject.projectDirectory.file("gradle/version.txt")).asText.getOrElse("")

val versionInt = versionString.split(".")[0].toInt() * 1000 + versionString.split(".")[1].toInt()

android {
    compileSdk = 36
    namespace = "org.example.${project.name.replace("-", ".")}"
    defaultConfig {
        namespace = "org.example.${project.name.replace("-", ".")}"
        minSdk = 36
        targetSdk = 36
        versionCode = versionInt
        versionName = versionString
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes.getByName("release") { minifyEnabled(false) }
}

// Required, because 'org.gradlex.jvm-dependency-conflict-resolution' does not
// automatically configure everything for Android (yet)
android {
    applicationVariants.all {
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

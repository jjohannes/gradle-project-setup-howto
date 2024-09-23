plugins {
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.base.dependency-rules")
    id("com.android.application")
}

val versionString =
    providers.fileContents(isolated.rootProject.projectDirectory.file("gradle/version.txt")).asText.getOrElse("")
val versionInt = versionString.split(".")[0].toInt() * 1000 + versionString.split(".")[1].toInt()

android {
    compileSdk = 34
    namespace = "org.example.product.${project.name}"
    defaultConfig {
        applicationId = "org.example.product.app"
        minSdk = 26
        targetSdk = 34
        versionCode = versionInt
        versionName = versionString

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes.getByName("release") { minifyEnabled(false) }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging { resources.excludes.add("META-INF/**") }
}

// Configure common test runtime dependencies for android application projects
dependencies { androidTestRuntimeOnly("androidx.test.espresso:espresso-core") }

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

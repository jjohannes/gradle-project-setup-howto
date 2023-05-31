plugins {
    id("org.example.base")
    id("com.android.application")
    id("org.example.consistent-resolution-android-application")
    id("org.owasp.dependencycheck")
}

val versionString = version as String
val versionInt = versionString.split(".")[0].toInt() * 1000 + versionString.split(".")[1].toInt()

android {
    compileSdk = 31
    namespace = "org.example.product.${project.name}"
    defaultConfig {
        applicationId = "org.example.product.app"
        minSdk = 26
        targetSdk = 31
        versionCode = versionInt
        versionName = versionString

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes.getByName("release") {
        minifyEnabled(false)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    packaging {
        resources.excludes.add("META-INF/**")
    }
}

// Configure common test runtime dependencies for android application projects
dependencies {
    androidTestRuntimeOnly("androidx.test.espresso:espresso-core")
}

dependencyCheck {
    scanConfigurations = listOf("releaseRuntimeClasspath")
    autoUpdate = false
}

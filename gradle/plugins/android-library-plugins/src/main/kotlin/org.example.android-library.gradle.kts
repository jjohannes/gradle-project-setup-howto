plugins {
    id("org.example.base")
    id("com.android.library")
    id("org.example.consistent-resolution-android-library")
}

android {
    compileSdk = 30
    namespace = "org.example.product.${project.name}"

    defaultConfig {
        minSdk = 26
    }
}

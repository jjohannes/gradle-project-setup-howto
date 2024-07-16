plugins {
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.base.dependency-rules")
    id("com.android.library")
}

android {
    compileSdk = 34
    namespace = "org.example.product.${project.name}"

    defaultConfig { minSdk = 26 }
}

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
    packaging { resources.excludes.add("META-INF/*") }
}

fun Configuration.configureConsistentResolution() {
    extendsFrom(configurations["internal"])
    shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"])
}

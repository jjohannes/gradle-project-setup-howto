plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation("com.autonomousapps:dependency-analysis-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin") // required for 'dependency-analysis-gradle-plugin' + Kotlin. See: https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/issues/432
}

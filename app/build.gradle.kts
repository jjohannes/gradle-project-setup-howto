plugins { id("org.example.gradle.component.application") }

// Complicated notation for 'capabilities' - upvote: https://github.com/gradle/gradle/issues/25629
dependencies {
    implementation(projects.bespin)
    implementation(projects.corellia)
    implementation(projects.kamino)
    implementation(projects.kashyyyk)
    implementation(projects.naboo)
    implementation(projects.tatooine)
    implementation(libs.android.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.guice)
    implementation(libs.guice.servlet)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)

    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.monitor)
    androidTestImplementation(libs.junit4)

    testImplementation(libs.junit.jupiter.api)
}

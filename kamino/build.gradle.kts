plugins { id("org.example.gradle.component.android-library") }

dependencies {
    api(projects.coruscant)
    api(libs.resteasy.core)
    implementation(libs.resteasy.guice)
    implementation(libs.resteasy.jackson2.provider)

    testImplementation(libs.junit.jupiter.api)
}

plugins { id("org.example.gradle.component.android-library") }

dependencies {
    api(projects.javarcaEngine)

    testImplementation(libs.junit.api)
}

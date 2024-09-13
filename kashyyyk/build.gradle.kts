plugins { id("org.example.gradle.component.library") }

dependencies {
    api(libs.kotlin.stdlib)

    testImplementation(libs.junit.jupiter.api)
}

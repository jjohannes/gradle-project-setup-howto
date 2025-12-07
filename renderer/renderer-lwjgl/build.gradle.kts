plugins { id("org.example.gradle.component.library") }

@Suppress("UnstableApiUsage")
dependencies {
    api(projects.javarcaEngine)
    implementation(libs.lwjgl)
    implementation(libs.lwjgl.glfw)
    implementation(libs.lwjgl.opengl)
    implementation(libs.lwjgl.stb)
    implementation(libs.slf4j.api)

    testImplementation(libs.junit.api)
}

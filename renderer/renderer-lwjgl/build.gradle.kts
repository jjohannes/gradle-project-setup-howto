plugins { id("org.example.gradle.component.library") }

@Suppress("UnstableApiUsage")
dependencies {
    api(projects.javarcaEngine)
    implementation(libs.lwjgl)
    implementation(libs.lwjgl.glfw)
    implementation(libs.lwjgl.opengl)
    implementation(libs.lwjgl.stb)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.lwjgl) { capabilities { requireFeature("natives") } }
    runtimeOnly(libs.lwjgl.glfw) { capabilities { requireFeature("natives") } }
    runtimeOnly(libs.lwjgl.opengl) { capabilities { requireFeature("natives") } }
    runtimeOnly(libs.lwjgl.stb) { capabilities { requireFeature("natives") } }

    testImplementation(libs.junit.api)
}

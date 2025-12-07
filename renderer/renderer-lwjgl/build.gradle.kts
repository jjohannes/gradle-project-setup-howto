plugins { id("org.example.gradle.component.library") }

@Suppress("UnstableApiUsage")
dependencies {
    api(projects.javarcaEngine)
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

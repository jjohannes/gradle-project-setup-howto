plugins { id("org.example.gradle.component.application") }

application { mainModule = "org.example.javarca.engine" }

dependencies {
    runtimeOnly(projects.jamcatchActors)
    runtimeOnly(projects.jamcatchStage)
    runtimeOnly(projects.javarcaEngine)
    runtimeOnly(projects.rendererLwjgl)
    runtimeOnly("org.slf4j:slf4j-jdk14")
}

plugins { id("org.example.gradle.component.application") }

application { mainClass = "org.example.javarca.engine.Engine" }

dependencies {
    runtimeOnly(projects.jamcatchActors)
    runtimeOnly(projects.jamcatchStage)
    runtimeOnly(projects.javarcaEngine)
    runtimeOnly(projects.rendererLwjgl)
    runtimeOnly("org.slf4j:slf4j-jdk14")
}

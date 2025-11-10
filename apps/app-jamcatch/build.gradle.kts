plugins { id("org.example.gradle.component.application") }

application { mainClass = "org.example.javarca.engine.Engine" }

dependencies {
    runtimeOnly(projects.jamcatchActors)
    runtimeOnly(projects.jamcatchAssets)
    runtimeOnly(projects.jamcatchStage)
    runtimeOnly(projects.javarcaEngine)
    runtimeOnly(projects.rendererLwjgl)
    runtimeOnly(libs.slf4j.simple)
}

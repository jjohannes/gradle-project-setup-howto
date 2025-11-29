plugins { id("org.example.gradle.component.webapplication") }

dependencies {
    implementation(projects.javarcaEngine)
    implementation(libs.spring.boot)
    implementation(libs.spring.boot.autoconfigure)
    implementation(libs.spring.context)
    implementation(libs.spring.web)
    runtimeOnly(projects.jamcatchActors)
    runtimeOnly(projects.jamcatchAssets)
    runtimeOnly(projects.jamcatchStage)
    runtimeOnly(projects.rendererLwjgl)
    runtimeOnly(libs.slf4j.simple)
    runtimeOnly(libs.spring.boot.starter.web)
}

tasks.bootRun {
    environment("PRESENTATION_FOLDER", layout.buildDirectory.dir("demo").get().asFile.absolutePath)
    environment("DEMO_MODE", "TRUE")
}

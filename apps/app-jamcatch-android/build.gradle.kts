plugins { id("org.example.gradle.component.android-application") }

dependencies {
    implementation(projects.rendererAndroid)
    runtimeOnly(projects.jamcatchActors)
    runtimeOnly(projects.jamcatchAssets)
    runtimeOnly(projects.jamcatchStage)
    runtimeOnly(projects.javarcaEngine)
    runtimeOnly(libs.slf4j.simple)
}

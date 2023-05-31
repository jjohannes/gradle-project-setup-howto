plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation(project(":dependency-analysis-plugins"))
    implementation(project(":dependency-rules-plugins"))
}

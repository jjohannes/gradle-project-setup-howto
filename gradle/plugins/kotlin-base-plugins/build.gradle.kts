plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))
    
    implementation(project(":base-plugins"))
}

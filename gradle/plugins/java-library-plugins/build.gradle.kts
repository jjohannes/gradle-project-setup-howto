plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation(project(":java-base-plugins"))
    implementation("org.springframework.boot:spring-boot-gradle-plugin")
}

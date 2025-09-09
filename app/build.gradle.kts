plugins { id("org.example.gradle.component.application") }

dependencies {
    implementation(projects.bespin)
    implementation(projects.corellia)
    implementation(projects.kamino)
    implementation(projects.kashyyyk)
    implementation(projects.naboo)
    implementation(projects.tatooine)
    implementation(libs.guice)
    implementation(libs.guice.servlet)
    implementation(libs.slf4j.api)
    implementation(libs.spring.boot)
    implementation(libs.spring.boot.autoconfigure)
    implementation(libs.spring.context)
    implementation(libs.spring.web)
    runtimeOnly(libs.slf4j.simple)
    runtimeOnly(libs.spring.boot.starter.web)

    mockApiImplementation(projects.app)
    mockApiImplementation(libs.guava)

    testImplementation(libs.junit.jupiter.api)

    testEndToEndImplementation(projects.app) { capabilities { requireFeature("mock-api") } }
    testEndToEndImplementation(libs.guava)
    testEndToEndImplementation(libs.junit.jupiter.api)
}

plugins { id("org.example.gradle.component.application") }

application { mainClass = "org.example.product.app.Application" }

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
    runtimeOnly(libs.slf4j.simple)
    providedCompile(libs.jakarta.servlet.api)

    mockApiImplementation(projects.app)
    mockApiImplementation(libs.guava)

    testImplementation(libs.junit.jupiter.api)
    testEndToEndImplementation(projects.app) { capabilities { requireFeature("mock-api") } }
    testEndToEndImplementation(libs.guava)
    testEndToEndImplementation(libs.junit.jupiter.api)
}

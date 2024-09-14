plugins { id("org.example.gradle.component.application") }

application { mainClass = "org.example.product.app.Application" }

// Complicated notation for 'capabilities' - upvote: https://github.com/gradle/gradle/issues/25629
dependencies {
    implementation(projects.bespin)
    implementation(projects.corellia)
    implementation(projects.kamino)
    implementation(projects.kashyyyk)
    implementation(projects.naboo)
    implementation(projects.tatooine)
    implementation(libs.guice)
    implementation(libs.guice.servlet)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.stdlib)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
    providedCompile(libs.jakarta.servlet.api)

    mockApiApi(libs.guava)
    mockApiApi(libs.kotlin.stdlib)
    mockApiImplementation(projects.app)

    testImplementation(libs.junit.jupiter.api)

    testEndToEndApi(libs.guava)
    testEndToEndApi(libs.junit.jupiter.api)
    testEndToEndApi(libs.kotlin.stdlib)
    testEndToEndImplementation(projects.app) { capabilities { requireCapability("${project.group}:$name-mock-api") } }
}

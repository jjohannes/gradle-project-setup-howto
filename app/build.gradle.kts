plugins { id("org.example.gradle.component.application") }

application { mainClass = "org.example.product.app.Application" }

dependencies {
    implementation(projects.bespin)
    implementation(projects.corellia)
    implementation(projects.kamino)
    implementation(projects.kashyyyk)
    implementation(projects.naboo)
    implementation(projects.tatooine)
    implementation("com.google.inject.extensions:guice-servlet")
    implementation("com.google.inject:guice")
    implementation("org.slf4j:slf4j-api")
    runtimeOnly("org.slf4j:slf4j-simple")
    providedCompile("jakarta.servlet:jakarta.servlet-api")

    mockApiImplementation(projects.app)
    mockApiImplementation("com.google.guava:guava")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testEndToEndImplementation(projects.app) { capabilities { requireFeature("mock-api") } }
    testEndToEndImplementation("com.google.guava:guava")
    testEndToEndImplementation("org.junit.jupiter:junit-jupiter-api")
}

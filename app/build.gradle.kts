plugins {
    id("org.example.application")
}

application {
    mainClass.set("org.example.product.app.Application")
}

dependencies {
    providedCompile("jakarta.servlet:jakarta.servlet-api")

    implementation(project(":bespin"))
    implementation(project(":corellia"))
    implementation(project(":kamino"))
    implementation(project(":kashyyyk"))
    implementation(project(":naboo"))
    implementation(project(":tatooine"))
    implementation("com.google.inject.extensions:guice-servlet")
    implementation("com.google.inject:guice")
    implementation("org.slf4j:slf4j-api")

    mockApiImplementation(project(path))
    mockApiImplementation("com.google.guava:guava")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    endToEndTestImplementation(project(path)) { capabilities { requireCapabilities("${project.group}:${project.name}-mock-api") } }
    endToEndTestImplementation("com.google.guava:guava")
    endToEndTestImplementation("org.junit.jupiter:junit-jupiter-api")

    runtimeOnly("org.slf4j:slf4j-simple")
}

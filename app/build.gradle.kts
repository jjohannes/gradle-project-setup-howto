plugins {
    id("org.example.application")
}

application {
    mainClass.set("org.example.product.app.ApplicationKt")
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.slf4j:slf4j-api")

    mockApiApi("com.google.guava:guava")
    mockApiApi("org.jetbrains.kotlin:kotlin-stdlib")
    mockApiImplementation(project(path))

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    endToEndTestApi("org.jetbrains.kotlin:kotlin-stdlib")
    endToEndTestApi("org.junit.jupiter:junit-jupiter-api")
    endToEndTestImplementation(project(path)) { capabilities { requireCapabilities("${project.group}:${project.name}-mock-api") } }
    endToEndTestImplementation("com.google.guava:guava")

    runtimeOnly("org.slf4j:slf4j-simple")
}

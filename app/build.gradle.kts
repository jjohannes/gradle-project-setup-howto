plugins {
    id("org.example.application")
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
    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-web")

    mockApiImplementation(project(path))
    mockApiImplementation("com.google.guava:guava")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    endToEndTestImplementation(project(path)) { capabilities { requireCapabilities("${project.group}:${project.name}-mock-api") } }
    endToEndTestImplementation("com.google.guava:guava")
    endToEndTestImplementation("org.junit.jupiter:junit-jupiter-api")

    runtimeOnly("org.slf4j:slf4j-simple")
    runtimeOnly("org.springframework.boot:spring-boot-starter-web")
}

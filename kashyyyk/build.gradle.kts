plugins {
    id("org.example.kotlin-library")
}

dependencies {
    api(project(":naboo"))
    api(project(":tatooine"))

    implementation(project(":bespin"))
    implementation(project(":kamino"))

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

plugins {
    id("org.example.kotlin-library-published")
}

dependencies {
    api("com.fasterxml.jackson.core:jackson-annotations")
    api("com.fasterxml.jackson.core:jackson-databind")
    api("com.google.guava:guava")
    api("jakarta.inject:jakarta.inject-api")
    api("org.json:json")
    api("org.opensaml:opensaml")

    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-json-org")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.sun.activation:jakarta.activation")
    implementation("com.sun.mail:jakarta.mail")
    implementation("org.apache.httpcomponents:fluent-hc")
    implementation("org.reflections:reflections")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

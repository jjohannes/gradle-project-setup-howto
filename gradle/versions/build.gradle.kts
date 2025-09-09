plugins {
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.check.format-gradle")
}

dependencies.constraints {
    api("com.fasterxml.jackson.core:jackson-annotations:2.13.5") { because("com.fasterxml.jackson.annotation") }
    api("com.fasterxml.jackson.core:jackson-core:2.13.5") { because("com.fasterxml.jackson.core") }
    api("com.google.guava:guava:30.1-jre") { because("com.google.common") }
    api("com.google.inject:guice:5.1.0") { because("com.google.guice") }
    api("com.sun.activation:jakarta.activation:2.0.1") { because("jakarta.activation") }
    api("com.sun.mail:jakarta.mail:2.0.1") { because("jakarta.mail") }
    api("jakarta.inject:jakarta.inject-api:2.0.1") { because("jakarta.inject") }
    api("jakarta.servlet:jakarta.servlet-api:6.1.0") { because("jakarta.servlet") }
    api("org.apache.httpcomponents:fluent-hc:4.5.13") { because("org.apache.httpcomponents.httpclient.fluent") }
    api("org.apache.poi:poi:5.2.2") { because("org.apache.poi.poi") }
    api("org.apache.velocity:velocity-engine-core:2.3") { because("velocity.engine.core") }
    api("org.assertj:assertj-core:3.22.0") { because("org.assertj.core") }
    api("org.junit.jupiter:junit-jupiter-api:5.8.2") { because("org.junit.jupiter.api") }
    api("org.slf4j:slf4j-api:2.0.13") { because("org.slf4j") }
    api("org.slf4j:slf4j-simple:2.0.13") { because("org.slf4j.simple") }
}

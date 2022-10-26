plugins {
    id("java-platform")
}

dependencies.constraints {
    api("dev.jacomet.gradle.plugins:logging-capabilities:0.11.1")
    api("io.fuchs.gradle.classpath-collision-detector:classpath-collision-detector:0.3")
    api("org.gradlex:java-ecosystem-capabilities:1.3.1")
    api("org.owasp:dependency-check-gradle:7.4.4")
}

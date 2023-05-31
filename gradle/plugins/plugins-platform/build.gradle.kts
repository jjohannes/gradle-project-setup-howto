plugins {
    id("java-platform")
}

dependencies.constraints {
    api("com.autonomousapps:dependency-analysis-gradle-plugin:1.27.0")
    api("dev.jacomet.gradle.plugins:logging-capabilities:0.11.1")
    api("io.fuchs.gradle.classpath-collision-detector:classpath-collision-detector:0.3")
    api("org.gradlex:extra-java-module-info:1.6")
    api("org.gradlex:java-ecosystem-capabilities:1.3.1")
    api("org.gradlex:java-module-dependencies:1.5")
    api("org.gradlex:java-module-testing:1.3")
    api("org.owasp:dependency-check-gradle:7.4.4")
}

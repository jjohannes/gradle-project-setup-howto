plugins {
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.check.dependency-versions")
    id("org.example.gradle.check.format-gradle")
}

dependencies.constraints {
    api("commons-io:commons-io:2.22.0")
    api("org.apache.commons:commons-csv:1.14.1")
    api("org.junit.jupiter:junit-jupiter-api:6.1.3")
    api("org.lwjgl:lwjgl:3.4.3")
    api("org.slf4j:slf4j-api:2.0.20")
}

tasks.checkVersionConsistency {
    excludes.add("org.junit.jupiter:junit-jupiter-api") // testing only
    excludes.add("org.assertj:assertj-core") // testing only
}

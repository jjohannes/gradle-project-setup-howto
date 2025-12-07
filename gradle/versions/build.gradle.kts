plugins {
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.check.dependency-versions")
    id("org.example.gradle.check.format-gradle")
}

dependencies.constraints {
    api("commons-io:commons-io:2.21.0")
    api("org.apache.commons:commons-csv:1.14.1")
    api("org.junit.jupiter:junit-jupiter-api:6.0.1")
    api("org.lwjgl:lwjgl:3.3.6")
    api("org.slf4j:slf4j-api:2.0.17")
}

tasks.checkVersionConsistency {
    excludes.add("org.junit.jupiter:junit-jupiter-api") // testing only
    excludes.add("org.assertj:assertj-core") // testing only
}

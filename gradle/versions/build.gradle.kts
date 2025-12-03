plugins {
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.feature.use-all-catalog-versions")
    id("org.example.gradle.check.dependency-versions")
    // id("org.example.gradle.check.format-gradle")
}

tasks.checkVersionConsistency {
    excludes.add("org.junit.jupiter:junit-jupiter-api") // testing only
    excludes.add("org.assertj:assertj-core") // testing only
}

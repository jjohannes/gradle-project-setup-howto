plugins {
    id("org.gradle.jacoco-report-aggregation")
    id("org.gradle.java")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
}

tasks.check {
    // Generate report when running 'check'
    dependsOn(tasks.testCodeCoverageReport)
}

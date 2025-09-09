plugins {
    id("org.gradle.jacoco-report-aggregation")
    id("org.gradle.java")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
}

// Make aggregation "classpath" use the platform for versions (gradle/versions)
configurations.aggregateCodeCoverageReportResults { extendsFrom(configurations["internal"]) }

// Generate report when running 'check'
tasks.check { dependsOn(tasks.testCodeCoverageReport) }

plugins {
    id("org.gradle.java")
    id("org.gradle.test-report-aggregation")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
}

// Make aggregation "classpath" use the platform for versions (gradle/versions)
configurations.aggregateTestReportResults { extendsFrom(configurations["internal"]) }

// Generate report when running 'check'
tasks.check { dependsOn(tasks.testAggregateTestReport) }

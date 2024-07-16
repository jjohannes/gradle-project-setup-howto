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

// Clear tasks group 'build' from clutter for a clean set of tasks to be used in daily work
tasks.buildDependents { setGroup(null) }

tasks.buildNeeded { setGroup(null) }

tasks.jar { setGroup(null) }

sourceSets.all { tasks.named(classesTaskName) { group = null } }

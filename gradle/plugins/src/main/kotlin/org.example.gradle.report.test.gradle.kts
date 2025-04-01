plugins {
    id("org.gradle.java")
    id("org.gradle.test-report-aggregation")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
}

// Make aggregation "classpath" use the platform for versions (gradle/versions)
configurations.aggregateTestReportResults { extendsFrom(configurations["internal"]) }

// Integrate testEndToEnd results into the aggregated UNIT_TEST test results
tasks.testAggregateTestReport {
    destinationDirectory = layout.buildDirectory.dir("reports/tests")
    testResults.from(
        configurations.aggregateTestReportResults
            .get()
            .incoming
            .artifactView {
                withVariantReselection()
                attributes {
                    attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.VERIFICATION))
                    attribute(TestSuiteName.TEST_SUITE_NAME_ATTRIBUTE, objects.named("testEndToEnd"))
                    attribute(
                        VerificationType.VERIFICATION_TYPE_ATTRIBUTE,
                        objects.named(VerificationType.TEST_RESULTS)
                    )
                }
            }
            .files
    )
}

// Generate report when running 'check'
tasks.check { dependsOn(tasks.testAggregateTestReport) }

// Clear tasks group 'build' from clutter for a clean set of tasks to be used in daily work
tasks.buildDependents { setGroup(null) }

tasks.buildNeeded { setGroup(null) }

tasks.jar { setGroup(null) }

sourceSets.all { tasks.named(classesTaskName) { group = null } }

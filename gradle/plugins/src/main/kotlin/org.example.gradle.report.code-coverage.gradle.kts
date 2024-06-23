plugins {
    id("org.gradle.jacoco-report-aggregation")
    id("org.gradle.java")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
}

// Make aggregation "classpath" use the platform for versions (gradle/versions)
configurations.aggregateCodeCoverageReportResults { extendsFrom(configurations["internal"]) }

// Integrate FUNCTIONAL_TEST results into the aggregated UNIT_TEST coverage results
tasks.testCodeCoverageReport {
    reports.html.outputLocation = layout.buildDirectory.dir("reports/coverage")
    reports.xml.outputLocation = layout.buildDirectory.file("reports/coverage.xml")
    executionData.from(
        configurations.aggregateCodeCoverageReportResults
            .get()
            .incoming
            .artifactView {
                withVariantReselection()
                attributes {
                    attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.VERIFICATION))
                    attribute(TestSuiteType.TEST_SUITE_TYPE_ATTRIBUTE, objects.named(TestSuiteType.FUNCTIONAL_TEST))
                    attribute(TestSuiteTargetName.TEST_SUITE_TARGET_NAME_ATTRIBUTE, objects.named("testEndToEnd"))
                    attribute(
                        VerificationType.VERIFICATION_TYPE_ATTRIBUTE,
                        objects.named(VerificationType.JACOCO_RESULTS)
                    )
                    attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, ArtifactTypeDefinition.BINARY_DATA_TYPE)
                }
            }
            .files
    )
}

// Generate report when running 'check'
tasks.check { dependsOn(tasks.testCodeCoverageReport) }

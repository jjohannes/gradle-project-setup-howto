plugins {
    id("lifecycle-base")
}

// Configure the ':tasks' task of the root project to only show
// the main lifecycle tasks as entry points to the build
val mainBuildGroup = "main build"
tasks.named<TaskReportTask>("tasks") {
    displayGroup = mainBuildGroup
}

tasks.named("build") {
    group = mainBuildGroup
    description = "Complete build of all modules and the application"
    dependsOn(subprojects.map { ":${it.name}:$name" })
}

tasks.register("compileAll") {
    group = mainBuildGroup
    description = "Complete build of all modules and the application"
    dependsOn(subprojects.map { ":${it.name}:$name" })
}

tasks.named("check") {
    group = mainBuildGroup
    description = "Runs all checks and produces test summary and code coverage reports"
    dependsOn(subprojects.map { ":${it.name}:$name" })
    dependsOn(gradle.includedBuild("platform").task(":check"))
    doLast {
        println("Unit test summary: app/build/reports/tests/unit-test/aggregated-results/index.html")
        println("Unit test code coverage: app/build/reports/jacoco/testCodeCoverageReport/html/index.html")
    }
}

tasks.register("run") {
    group = mainBuildGroup
    description = "Build and run as standalone application"
    dependsOn(":app:$name")
}

tasks.register("deployWebApp") {
    group = mainBuildGroup
    description = "Deploy web app into local Tomcat found via CATALINA_HOME"
    dependsOn(":app:$name")
}

tasks.register("checkForDependencyVulnerabilities") {
    group = mainBuildGroup
    description = "Check current dependencies for known vulnerabilities"
    dependsOn(":app:dependencyCheckAnalyze")
}

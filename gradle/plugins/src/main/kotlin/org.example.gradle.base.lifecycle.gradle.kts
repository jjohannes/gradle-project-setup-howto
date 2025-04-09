plugins { id("org.gradle.base") }

// Convenience for local development: when running './gradlew' without parameters show the tasks...
defaultTasks("tasks")

if (gradle.startParameter.taskNames.isEmpty()) {
    // ...of group 'build' only
    tasks.withType<TaskReportTask>().configureEach { displayGroup = "build" }
}

tasks.register("qualityCheck") {
    group = "verification"
    description = "Runs checks (without executing tests)"
    dependsOn(tasks.assemble)
}

tasks.register("qualityGate") {
    group = "build"
    description = "Runs checks and auto-corrects (without executing tests)"
    dependsOn(tasks.assemble)
}

tasks.check { dependsOn(tasks.named("qualityCheck")) }

// Cleanup the 'build' group by removing all tasks developers usually do not need to call directly
afterEvaluate {
    tasks.configureEach {
        if (name in listOf("buildDependents", "buildNeeded", "classes")) {
            group = null
        }
        if (name.endsWith("Classes")) {
            group = null
        }
        if (this is Jar) {
            setGroup(null)
        }
        if (this is Test) {
            group = "build"
        }
        // added by Kotlin if used
        if (name in listOf("buildKotlinToolingMetadata", "kotlinSourcesJar")) {
            group = null
        }
        // added by Spring Boot plugin if used
        if (name in listOf("resolveMainClassName", "resolveTestMainClassName")) {
            group = null
        }
    }
}

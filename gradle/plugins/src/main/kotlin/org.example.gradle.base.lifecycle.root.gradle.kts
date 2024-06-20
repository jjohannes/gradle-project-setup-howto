plugins {
    id("org.gradle.base")
    id("org.example.gradle.base.lifecycle")
}

// Configure the 'tasks' task such that when you run './gradlew' without arguments you see only the
// tasks of the 'build' group, which are the tasks for daily development.
defaultTasks("tasks")

tasks.named<TaskReportTask>("tasks") { displayGroup = "build" }

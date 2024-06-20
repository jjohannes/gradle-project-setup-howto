plugins { id("org.gradle.base") }

tasks.register("qualityCheck") {
    group = "build"
    description = "Runs checks (without executing tests)"
}

tasks.register("qualityGate") {
    group = "build"
    description = "Runs checks and auto-corrects (without executing tests)"
}

tasks.check { dependsOn(tasks.named("qualityCheck")) }

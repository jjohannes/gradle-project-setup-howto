plugins {
    id("com.diffplug.spotless")
    id("org.example.gradle.base.lifecycle")
}

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        palantirJavaFormat()
    }
}

tasks.named("qualityCheck") { dependsOn(tasks.spotlessCheck) }

tasks.named("qualityGate") { dependsOn(tasks.spotlessApply) }

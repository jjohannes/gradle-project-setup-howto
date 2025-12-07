import org.gradlex.javamodule.dependencies.tasks.ModuleDirectivesScopeCheck

plugins {
    id("org.gradle.java")
    id("com.autonomousapps.dependency-analysis")
    id("io.fuchs.gradle.classpath-collision-detector")
    id("org.example.gradle.base.lifecycle")
}

tasks.named("qualityCheck") {
    dependsOn(tasks.detectCollisions)
    dependsOn(tasks.withType<ModuleDirectivesScopeCheck>())
}

tasks.named("qualityGate") {
    dependsOn(tasks.detectCollisions)
    dependsOn(tasks.withType<ModuleDirectivesScopeCheck>())
}

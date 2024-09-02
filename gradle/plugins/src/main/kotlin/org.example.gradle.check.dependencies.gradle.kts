import com.autonomousapps.DependencyAnalysisSubExtension
import com.autonomousapps.tasks.ProjectHealthTask

plugins {
    id("org.gradle.java")
    id("com.autonomousapps.dependency-analysis")
    id("io.fuchs.gradle.classpath-collision-detector")
    id("org.example.gradle.base.lifecycle")
}

// Configure the dependency analysis plugin to fail if issues are found
configure<DependencyAnalysisSubExtension> { issues { onAny { severity("fail") } } }

tasks.named("qualityCheck") {
    dependsOn(tasks.detectCollisions)
    dependsOn(tasks.withType<ProjectHealthTask>())
}

tasks.named("qualityGate") {
    dependsOn(tasks.detectCollisions)
    dependsOn(tasks.withType<ProjectHealthTask>())
}

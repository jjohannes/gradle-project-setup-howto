import com.diffplug.spotless.LineEnding

plugins {
    id("org.example.gradle.base.lifecycle")
    id("com.diffplug.spotless")
}

// https://github.com/gradle/gradle/issues/25469#issuecomment-3444231151
spotless { lineEndings = LineEnding.UNIX }

tasks.named("qualityCheck") { dependsOn(tasks.spotlessCheck) }

tasks.named("qualityGate") { dependsOn(tasks.spotlessApply) }

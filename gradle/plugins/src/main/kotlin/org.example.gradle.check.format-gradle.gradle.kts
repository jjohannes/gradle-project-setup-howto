plugins {
    id("com.diffplug.spotless")
    id("org.example.gradle.base.lifecycle")
}

spotless { kotlinGradle { ktfmt().kotlinlangStyle().configure { it.setMaxWidth(500) } } }

tasks.named("qualityCheck") { dependsOn(tasks.spotlessCheck) }

tasks.named("qualityGate") { dependsOn(tasks.spotlessApply) }

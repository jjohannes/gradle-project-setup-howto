plugins {
    id("com.diffplug.spotless")
    id("org.example.gradle.base.lifecycle")
}

spotless {
    kotlinGradle {
        ktfmt().kotlinlangStyle().configure { it.setMaxWidth(120) }
        target("settings.gradle.kts", "gradle/plugins/build.gradle.kts", "gradle/plugins/src/main/**/*.gradle.kts")
    }
    kotlin {
        ktfmt().kotlinlangStyle().configure { it.setMaxWidth(120) }
        target("gradle/plugins/src/main/**/*.kt")
    }
}

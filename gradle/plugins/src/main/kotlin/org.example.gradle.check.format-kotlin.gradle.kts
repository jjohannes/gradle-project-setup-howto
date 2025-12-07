plugins {
    id("com.diffplug.spotless")
    id("org.example.gradle.base.lifecycle")
}

spotless { kotlin { ktfmt().kotlinlangStyle() } }

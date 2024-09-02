plugins {
    id("org.example.gradle.base.lifecycle.root")
    id("org.example.gradle.check.format-gradle.root")
    // https://github.com/autonomousapps/dependency-analysis-gradle-plugin/issues/1256
    id("com.autonomousapps.dependency-analysis")
}

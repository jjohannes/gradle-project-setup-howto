plugins {
    id("org.gradle.application")
    id("org.example.gradle.feature.test-fixtures")
}

application { mainClass = "org.example.javarca.engine.Engine" }

// Ideally, it would be poissible to express that this project on its own is not an application
// https://github.com/autonomousapps/dependency-analysis-gradle-plugin/issues/761
dependencyAnalysis.issues { onIncorrectConfiguration { exclude(":javarca-model") } }

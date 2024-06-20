plugins {
    id("org.gradle.base")
    id("com.autonomousapps.dependency-analysis")
}

// Configure the dependency analysis plugin to fail if issues are found
dependencyAnalysis { issues { all { onAny { severity("fail") } } } }

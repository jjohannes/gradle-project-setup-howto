plugins {
    id("java-platform")
    id("org.example.base")
    id("org.example.dependency-analysis-platform")
}

// Depend on other Platforms/BOMs to align versions for libraries that consist of multiple components (like Jackson)
javaPlatform.allowDependencies()

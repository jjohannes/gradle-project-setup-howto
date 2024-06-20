// Include all subfolders that contain a 'build.gradle.kts' as subprojects
rootDir
    .listFiles()
    ?.filter { File(it, "build.gradle.kts").exists() }
    ?.forEach { subproject -> include(subproject.name) }

// Platform project
include(":versions")

project(":versions").projectDir = file("gradle/versions")

// Aggregation and analysis project to create reports about the whole software (coverage, SBOM, ...)
include(":aggregation")

project(":aggregation").projectDir = file("gradle/aggregation")

// Allow local projects to be referred to by accessor
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

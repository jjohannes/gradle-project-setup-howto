plugins {
    id("org.gradlex.jvm-dependency-conflict-resolution")
    id("io.mvnpm.gradle.plugin.native-java-plugin")
}

jvmDependencyConflicts {
    // Configure build wide consistent resolution. That is, the versions that are used on the
    // runtime classpath of the  web applications should also be used in all other places
    // (e.g. also when compiling a project at the bottom of the dependency graph that does not
    // see most of the other dependencies that may influence the version choices).
    consistentResolution {
        platform(":versions")
        providesVersions(":aggregation")
    }

    // Configure logging capabilities to default to Slf4JSimple
    logging { enforceSlf4JSimple() }

    // Add missing information to metadata of 3rd-party libraries.
    patch {
        module("com.google.guava:guava") {
            // remove annotation libraries we do not need
            removeDependency("com.google.j2objc:j2objc-annotations")
            removeDependency("org.checkerframework:checker-qual")
        }
    }
}

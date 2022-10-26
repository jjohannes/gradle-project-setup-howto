plugins {
    id("java-base")
}

// Expose the ':app' project runtime classpath in every project
val app = configurations.dependencyScope("app") {
    withDependencies {
        // Depend on ':app' and with this on all its (transitive) dependencies
        add(project.dependencies.create(project(":app")))
        // Get our own version information from the platform project
        add(project.dependencies.create(project.dependencies.platform("org.example.product:platform")))
    }
}
val appRuntimeClasspath = configurations.resolvable("appRuntimeClasspath") {
    description = "Runtime classpath of the complete application"
    extendsFrom(app.get())
    attributes {
        // We want the runtime classpath represented by Usage.JAVA_RUNTIME
        attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
    }
}

// Every compile classpath and runtime classpath uses the versions of the
sourceSets.all {
    configurations[compileClasspathConfigurationName].shouldResolveConsistentlyWith(appRuntimeClasspath.get())
    configurations[runtimeClasspathConfigurationName].shouldResolveConsistentlyWith(appRuntimeClasspath.get())
    // Source sets without production code (tests / fixtures) are allowed to have dependencies that are
    // not part of the consistent resolution result and might need additional version information
    if (this != sourceSets["main"]) {
        dependencies.add(implementationConfigurationName, dependencies.platform("org.example.product:platform"))
    }
}

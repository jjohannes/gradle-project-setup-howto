plugins {
    id("com.android.library")
    id("org.example.consistent-resolution")
}

android {
    val appRuntimeClasspath = configurations["appRuntimeClasspath"]
    libraryVariants.all {
        compileConfiguration.shouldResolveConsistentlyWith(appRuntimeClasspath)
        runtimeConfiguration.shouldResolveConsistentlyWith(appRuntimeClasspath)

        unitTestVariant.compileConfiguration.shouldResolveConsistentlyWith(appRuntimeClasspath)
        unitTestVariant.runtimeConfiguration.shouldResolveConsistentlyWith(appRuntimeClasspath)
        dependencies.add(unitTestVariant.compileConfiguration.name, dependencies.platform("org.example.product:platform"))
        dependencies.add(unitTestVariant.runtimeConfiguration.name, dependencies.platform("org.example.product:platform"))

        if (testVariant != null) {
            testVariant.compileConfiguration.shouldResolveConsistentlyWith(appRuntimeClasspath)
            testVariant.runtimeConfiguration.shouldResolveConsistentlyWith(appRuntimeClasspath)
            dependencies.add(testVariant.compileConfiguration.name, dependencies.platform("org.example.product:platform"))
            dependencies.add(testVariant.runtimeConfiguration.name, dependencies.platform("org.example.product:platform"))
        }
    }
}
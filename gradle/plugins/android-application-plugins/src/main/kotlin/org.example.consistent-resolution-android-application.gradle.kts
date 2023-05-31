plugins {
    id("com.android.application")
    id("org.example.consistent-resolution")
}

android {
    applicationVariants.all {
        dependencies.add(runtimeConfiguration.name, dependencies.platform("org.example.product:platform"))
        compileConfiguration.shouldResolveConsistentlyWith(runtimeConfiguration)

        unitTestVariant.compileConfiguration.shouldResolveConsistentlyWith(runtimeConfiguration)
        unitTestVariant.runtimeConfiguration.shouldResolveConsistentlyWith(runtimeConfiguration)
        dependencies.add(unitTestVariant.compileConfiguration.name, dependencies.platform("org.example.product:platform"))
        dependencies.add(unitTestVariant.runtimeConfiguration.name, dependencies.platform("org.example.product:platform"))

        if (testVariant != null) {
            testVariant.compileConfiguration.shouldResolveConsistentlyWith(runtimeConfiguration)
            testVariant.runtimeConfiguration.shouldResolveConsistentlyWith(runtimeConfiguration)
            dependencies.add(testVariant.compileConfiguration.name, dependencies.platform("org.example.product:platform"))
            dependencies.add(testVariant.runtimeConfiguration.name, dependencies.platform("org.example.product:platform"))
        }
    }
}
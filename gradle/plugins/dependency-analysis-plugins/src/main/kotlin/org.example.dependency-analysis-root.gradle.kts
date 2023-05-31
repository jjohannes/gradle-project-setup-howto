plugins {
    id("com.autonomousapps.dependency-analysis")
}

// Lifecycle task to check dependency scopes in all subprojects
tasks.register("checkDependencyScopes") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check all dependency scopes (api vs implementation) and find unused dependencies"
    dependsOn(subprojects.map { "${it.path}:checkDependencyScopes"})
}

plugins {
    id("com.autonomousapps.dependency-analysis")
}

// Lifecycle task to check dependency scopes in all subprojects
tasks.register("checkAllModuleInfo") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check scope and order of directives in 'module-info.java' files"
    dependsOn(subprojects.map { "${it.path}:checkAllModuleInfo"})
}

import org.example.dependencyanalysis.DependencyFormatCheck
import org.example.dependencyanalysis.PlatformVersionConsistencyCheck

plugins {
    id("java-platform")
    id("org.gradlex.java-module-dependencies")
}

val checkDependencyFormatting = tasks.register<DependencyFormatCheck>("checkDependencyFormatting") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP

    buildFilePath.set(project.buildFile.absolutePath)
    declaredDependencies.put("api", provider { configurations.api.get().dependencies.map { d -> d.toDeclaredString() } })
    declaredDependencies.put("runtime", provider { configurations.runtime.get().dependencies.map { d -> d.toDeclaredString() } })
    declaredConstraints.put("api", provider { configurations.api.get().dependencyConstraints.map { d -> d.toDeclaredString() } })
    declaredConstraints.put("runtime", provider { configurations.runtime.get().dependencyConstraints.map { d -> d.toDeclaredString() } })
}

// Install a task that checks the consistency of the platform against the resolution result of the product
val product = configurations.dependencyScope("product").get()
dependencies {
    product(platform(project(path)))
}
val purePlatformVersions = configurations.dependencyScope("purePlatformVersions") {
    withDependencies {
        add(project.dependencies.platform(project(project.path)))
        // Create a dependency for each constraint defined in the platform (this is to check for unused entries)
        configurations.api.get().dependencyConstraints.forEach { constraint ->
            add(project.dependencies.create("${constraint.group}:${constraint.name}") { isTransitive = false })
        }
    }
}
val fullProductRuntimeClasspath = configurations.resolvable("fullProductRuntimeClasspath") {
    attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
    attributes.attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
    extendsFrom(product)
}
val purePlatformVersionsPath = configurations.resolvable("purePlatformVersionsPath") {
    attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
    attributes.attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
    extendsFrom(purePlatformVersions.get())
}
tasks.register<PlatformVersionConsistencyCheck>("checkPlatformVersionConsistency") {
    group = HelpTasksPlugin.HELP_GROUP
    productClasspath.set(fullProductRuntimeClasspath.map { it.incoming.resolutionResult.allComponents })
    classpathFromPlatform.set(purePlatformVersionsPath.map { it.incoming.resolutionResult.allComponents })
}

tasks.check {
    dependsOn(checkDependencyFormatting)
}

fun Dependency.toDeclaredString() = "$group:$name:$version"
fun DependencyConstraint.toDeclaredString() = "version(\"${javaModuleDependencies.moduleName("$group:$name").getOrElse("$group:$name")}\", \"$version\")"

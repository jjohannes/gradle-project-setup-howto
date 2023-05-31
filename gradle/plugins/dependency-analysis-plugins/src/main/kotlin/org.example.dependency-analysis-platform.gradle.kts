import org.example.dependencyanalysis.DependencyFormatCheck
import org.example.dependencyanalysis.DependencyVersionUpgradesCheck
import org.example.dependencyanalysis.PlatformVersionConsistencyCheck

plugins {
    id("java-platform")
}

val checkDependencyFormatting = tasks.register<DependencyFormatCheck>("checkDependencyFormatting") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP

    buildFilePath.set(project.buildFile.absolutePath)
    shouldNotHaveVersions.set(false)
    declaredDependencies.put("api", provider { configurations.api.get().dependencies.map { d -> d.toDeclaredString() } })
    declaredDependencies.put("runtime", provider { configurations.runtime.get().dependencies.map { d -> d.toDeclaredString() } })
    declaredConstraints.put("api", provider { configurations.api.get().dependencyConstraints.map { d -> d.toDeclaredString() } })
    declaredConstraints.put("runtime", provider { configurations.runtime.get().dependencyConstraints.map { d -> d.toDeclaredString() } })
}

val latestReleases = configurations.dependencyScope("dependencyVersionUpgrades") {
    withDependencies {
        add(project.dependencies.platform(project(project.path)))
        configurations.api.get().dependencies.forEach {
            add(project.dependencies.platform("${it.group}:${it.name}:latest.release") { isTransitive = false })
        }
        configurations.api.get().dependencyConstraints.forEach {
            add(project.dependencies.create("${it.group}:${it.name}:latest.release") { isTransitive = false })
        }
    }
}
val latestReleasesPath = configurations.resolvable("latestReleasesPath") {
    attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage.JAVA_RUNTIME))
    attributes.attribute(Category.CATEGORY_ATTRIBUTE, objects.named(Category.LIBRARY))
    extendsFrom(latestReleases.get())
}
tasks.register<DependencyVersionUpgradesCheck>("checkForDependencyVersionUpgrades") {
    group = HelpTasksPlugin.HELP_GROUP
    projectName.set(project.name)
    apiDependencies.set(configurations.api.get().dependencies.map { "${it.group}:${it.name}:${it.version}" })
    apiDependencyConstraints.set(configurations.api.get().dependencyConstraints.map { "${it.group}:${it.name}:${it.version}" })
    latestReleasesResolutionResult.set(latestReleasesPath.map { it.incoming.resolutionResult.allComponents })
}

// Install a task that checks the consistency of the platform against the resolution result of the product
val product = configurations.dependencyScope("product").get()
dependencies {
    product(platform(project(path)))
}
val purePlatformVersions = configurations.dependencyScope("purePlatformVersions") {
    withDependencies {
        add(project.dependencies.platform(project(project.path)))
        // Create a dependency for eac constraint defined in the platform (this is to check for unused entries)
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
fun DependencyConstraint.toDeclaredString() = "$group:$name:$version"

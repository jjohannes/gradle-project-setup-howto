import org.example.gradle.spotless.SortDependenciesStep

plugins { id("com.diffplug.spotless") }

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        palantirJavaFormat()
        addStep(SortDependenciesStep.create())
    }
}

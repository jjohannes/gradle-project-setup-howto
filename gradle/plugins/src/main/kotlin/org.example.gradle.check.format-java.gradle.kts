plugins { id("com.diffplug.spotless") }

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        palantirJavaFormat()
    }
}

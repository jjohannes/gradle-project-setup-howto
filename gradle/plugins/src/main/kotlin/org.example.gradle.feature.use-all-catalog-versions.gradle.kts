plugins { id("org.gradle.java-platform") }

// Allow upgrading/downgrading (transitive) versions via catalog by adding strict constraints
dependencies.constraints {
    val libs = versionCatalogs.named("libs")
    val catalogEntries = libs.libraryAliases.map { libs.findLibrary(it).get().get() }
    catalogEntries.forEach { entry ->
        val version = entry.version
        if (version != null) {
            api(entry) { version { require(version) } }
        }
    }
}

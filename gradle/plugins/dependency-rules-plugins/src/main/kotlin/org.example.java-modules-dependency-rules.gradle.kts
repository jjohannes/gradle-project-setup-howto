plugins {
    id("org.gradlex.extra-java-module-info")
    id("org.gradlex.java-module-dependencies")
}

extraJavaModuleInfo {
    failOnAutomaticModules.set(true)

    module("com.google.code.findbugs:jsr305", "javax.annotations.jsr305") {
        exports("javax.annotation")
        requireAllDefinedDependencies()
    }
    module("com.google.guava:guava", "com.google.common") {
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("com.github.virtuald:curvesapi", "com.github.virtuald.curvesapi") { // required by org.apache.poi.poi
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("com.zaxxer:SparseBitSet", "SparseBitSet") { // required by org.apache.poi.poi
        exports("com.zaxxer.sparsebits")
        requireAllDefinedDependencies()
    }
    module("commons-codec:commons-codec", "org.apache.commons.codec") { // required by org.apache.poi.poi
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("org.apache.commons:commons-collections4", "org.apache.commons.collections4") { // required by org.apache.poi.poi
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("org.apache.commons:commons-compress", "org.apache.commons.compress") { // required by org.apache.poi.poi
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("commons-io:commons-io", "org.apache.commons.io") { // required by org.apache.poi.poi
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("org.apache.commons:commons-lang3", "org.apache.commons.lang3") { // required by velocity
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("org.apache.commons:commons-math3", "commons.math3") { // required by org.apache.poi.poi
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("org.apache.velocity:velocity-engine-core", "velocity.engine.core") {
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    module("org.hamcrest:hamcrest-core", "org.hamcrest.core") {
        exportAllPackages()
        requireAllDefinedDependencies()
    }
    knownModule("org.slf4j:slf4j-api", "org.slf4j")
}

javaModuleDependencies {
    // Override because there are multiple options
    moduleNameToGA.put("jakarta.activation", "com.sun.activation:jakarta.activation")
    moduleNameToGA.put("jakarta.mail", "com.sun.mail:jakarta.mail")
 }

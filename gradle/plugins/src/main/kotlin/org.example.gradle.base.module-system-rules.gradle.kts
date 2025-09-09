plugins { id("org.gradlex.extra-java-module-info") }

extraJavaModuleInfo {
    failOnAutomaticModules = true

    module("com.github.virtuald:curvesapi", "com.github.virtuald.curvesapi")
    module("com.google.code.findbugs:jsr305", "javax.annotations.jsr305")
    module("com.google.errorprone:error_prone_annotations", "com.google.errorprone.annotations")
    module("com.google.guava:failureaccess", "com.google.common.internal.failureaccess")
    module("com.google.guava:guava", "com.google.common")
    module("com.zaxxer:SparseBitSet", "SparseBitSet")
    module("commons-codec:commons-codec", "org.apache.commons.codec")
    module("commons-io:commons-io", "org.apache.commons.io")
    module("org.apache.commons:commons-collections4", "org.apache.commons.collections4")
    module("org.apache.commons:commons-compress", "org.apache.commons.compress")
    module("org.apache.commons:commons-lang3", "org.apache.commons.lang3")
    module("org.apache.commons:commons-math3", "commons.math3")
    module("org.apache.velocity:velocity-engine-core", "velocity.engine.core")
    module("org.hamcrest:hamcrest-core", "org.hamcrest.core")
}

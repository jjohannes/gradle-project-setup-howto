plugins { id("org.gradlex.extra-java-module-info") }

extraJavaModuleInfo {
    failOnAutomaticModules = true

    // Add for modules that miss module-info.class files:
    // module("commons-codec:commons-codec", "org.apache.commons.codec")
}

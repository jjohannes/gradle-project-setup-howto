plugins { id("org.gradle.java-library") }

java.registerFeature("test") { usingSourceSet(sourceSets.test.get()) }

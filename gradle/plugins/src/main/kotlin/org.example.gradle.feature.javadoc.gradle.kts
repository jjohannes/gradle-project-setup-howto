plugins { id("org.gradle.java") }

// Publish/build with Javadoc
java { withJavadocJar() }

tasks.withType<Javadoc>().configureEach {
    options {
        this as StandardJavadocDocletOptions
        encoding = "UTF-8"
        addStringOption("Xwerror", "-Xdoclint:all,-missing")
    }
}

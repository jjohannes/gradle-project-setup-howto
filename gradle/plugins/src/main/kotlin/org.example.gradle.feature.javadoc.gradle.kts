plugins { id("org.gradle.java") }

tasks.withType<Javadoc>().configureEach {
    options {
        this as StandardJavadocDocletOptions
        encoding = "UTF-8"
        addStringOption("Xwerror", "-Xdoclint:all,-missing")
    }
}

tasks.named("qualityCheck") { dependsOn(tasks.withType<Javadoc>()) }

plugins { id("org.gradle.java") }

tasks.processResources { from(isolated.rootProject.projectDirectory.file("gradle/version.txt")) }

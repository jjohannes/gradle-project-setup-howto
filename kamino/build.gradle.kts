plugins {
    id("org.example.java-library-published")
}

dependencies {
    api(project(":coruscant"))

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

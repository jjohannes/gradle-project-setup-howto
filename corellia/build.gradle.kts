plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.test-fixtures")
}

dependencies {
    api("org.apache.poi:poi")
    implementation("commons-io:commons-io")
    implementation("org.apache.poi:poi-ooxml")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    testFixturesApi("com.google.code.findbugs:jsr305")
    testFixturesApi("com.google.guava:guava")
}

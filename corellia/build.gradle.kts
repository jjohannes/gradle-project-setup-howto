plugins {
    id("org.example.kotlin-library-with-test-fixtures")
}

dependencies {
    api("org.apache.poi:poi")
    api("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("commons-io:commons-io")
    implementation("org.apache.poi:poi-ooxml")

    testFixturesApi("com.google.code.findbugs:jsr305")
    testFixturesApi("com.google.guava:guava")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

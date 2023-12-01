plugins {
    id("org.example.kotlin-library")
}

dependencies {
    api("com.github.racc:typesafeconfig-guice")
    api("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.apache.zookeeper:zookeeper")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

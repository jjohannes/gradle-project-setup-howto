plugins {
    id("org.example.java-library")
}

dependencies {
    api("com.github.racc:typesafeconfig-guice")

    implementation("org.apache.zookeeper:zookeeper")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

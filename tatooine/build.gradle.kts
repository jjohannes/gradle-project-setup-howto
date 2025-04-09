plugins { id("org.example.gradle.component.library") }

dependencies {
    api("com.github.racc:typesafeconfig-guice")
    implementation("org.apache.zookeeper:zookeeper")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}

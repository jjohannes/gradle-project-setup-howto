plugins { id("org.example.gradle.component.library") }

dependencies {
    api(libs.typesafeconfig.guice)
    implementation(libs.zookeeper)

    testImplementation(libs.junit.jupiter.api)
}

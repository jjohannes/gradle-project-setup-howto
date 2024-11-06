plugins { id("org.example.gradle.component.library") }

dependencies {
    api(libs.kotlin.stdlib)
    api(libs.typesafeconfig.guice)
    implementation(libs.zookeeper)

    testImplementation(libs.junit.jupiter.api)
}

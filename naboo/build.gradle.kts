plugins { id("org.example.gradle.component.library") }

dependencies {
    api(libs.kotlin.stdlib)
    implementation(libs.solr.solrj)
    implementation(libs.zookeeper.jute)

    testImplementation(libs.junit.jupiter.api)
}

plugins {
    id("org.example.gradle.component.library")
    id("org.example.gradle.feature.test-fixtures")
}

dependencies {
    api(libs.kotlin.stdlib)
    api(libs.poi)
    implementation(libs.commons.io)
    implementation(libs.poi.ooxml)

    testImplementation(libs.junit.jupiter.api)

    testFixturesApi(libs.guava)
    testFixturesApi(libs.jsr305)
}

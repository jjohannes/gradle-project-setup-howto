plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(project(":build-parameters-plugins"))
    implementation("com.gradle:gradle-enterprise-gradle-plugin:3.15.1")
}

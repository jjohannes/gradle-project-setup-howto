plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))

    implementation(project(":java-base-plugins"))
    implementation("com.android.tools.build:gradle")
    implementation("org.owasp:dependency-check-gradle")
}

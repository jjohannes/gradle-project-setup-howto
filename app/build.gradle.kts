plugins {
    id("org.example.android-application")
}

dependencies {
    implementation(project(":bespin"))
    implementation(project(":corellia"))
    implementation(project(":kamino"))
    implementation(project(":kashyyyk"))
    implementation(project(":naboo"))
    implementation(project(":tatooine"))
    implementation("androidx.appcompat:appcompat")
    implementation("com.google.android.material:material")
    implementation("com.google.inject.extensions:guice-servlet")
    implementation("com.google.inject:guice")
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")

    androidTestImplementation("androidx.test.ext:junit")
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(platform(project(":plugins-platform")))
    
    implementation(project(":base-plugins"))
    implementation("com.android.tools.build:gradle") // To access BuildTypeAttr
}

plugins {
    id("org.example.platform")
}

dependencies {
    api(platform("com.fasterxml.jackson:jackson-bom:2.13.4"))
    api(platform("org.apache.poi:poi:5.2.2")) { because ("see PoiPlatformRule") } // Central component as Anchor for Alignment BOM
    api(platform("org.junit:junit-bom:5.7.2")) { (this as ExternalModuleDependency).version { reject("[5.8.0,)") } } // Do not Upgrade to 5.8: https://github.com/gradle/gradle/issues/18627
    api(platform("org.mockito:mockito-bom:4.8.0"))
    api(platform("org.slf4j:slf4j-parent:2.0.3")) { because ("see Slf4jPlatformRule") } // Parent as Anchor for Alignment BOM
}

moduleInfo {
    version("com.google.common", "30.1-jre")
    version("jakarta.activation") { require("1.2.2"); reject("[2.0.0,)") } // Upgrade to 2.x requires newer Jakarta APIs
    version("jakarta.mail") { require("1.6.7"); reject("[2.0.0,)") } // Upgrade to 2.x requires newer Jakarta APIs
    version("jakarta.servlet", "6.0.0")
    version("java.inject") { require("1.0.5"); reject("[2.0.0,)") } // Upgrade to 2.x requires newer Jakarta APIs
    version("javax.annotations.jsr305", "3.0.2")
    version("org.assertj.core", "3.22.0")
    version("velocity.engine.core", "2.3")
}

plugins {
    id("org.example.platform")
}

dependencies {
    api(platform("com.fasterxml.jackson:jackson-bom:2.13.2.20220328"))
    api(platform("com.google.inject:guice-bom:5.1.0"))
    api(platform("org.apache.httpcomponents:httpcomponents-client:4.5.13")) { because ("see HttpComponentsPlatformRule") } // Parent as Anchor for Alignment BOM
    api(platform("org.apache.poi:poi:5.2.2")) { because ("see PoiPlatformRule") } // Central component as Anchor for Alignment BOM
    api(platform("org.jboss.resteasy:resteasy-bom:4.7.6.Final")) { (this as ExternalModuleDependency).version { reject("[5.0.0.Final,)") } }
    api(platform("org.junit:junit-bom:5.7.2")) { (this as ExternalModuleDependency).version { reject("[5.8.0,)") } } // Do not Upgrade to 5.8: https://github.com/gradle/gradle/issues/18627
    api(platform("org.mockito:mockito-bom:4.5.1"))
    api(platform("org.slf4j:slf4j-parent:1.7.36")) { because ("see Slf4jPlatformRule") } // Parent as Anchor for Alignment BOM
}

dependencies.constraints {
    api("com.github.racc:typesafeconfig-guice:0.1.0")
    api("com.sun.activation:jakarta.activation:1.2.2") { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api("com.sun.mail:jakarta.mail:1.6.7") { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api("jakarta.inject:jakarta.inject-api:1.0.5") { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api("jakarta.servlet:jakarta.servlet-api:4.0.4") { version { reject("[5.0.0,)") } } // Stay Tomcat 8 compatible
    api("org.apache.commons:commons-lang3:3.9")
    api("org.apache.solr:solr-solrj:7.7.3") { version { reject("[8.0.0,)") } } // API changes in 8 require production code changes
    api("org.apache.velocity:velocity-engine-core:2.3")
    api("org.apache.zookeeper:zookeeper:3.8.0")
    api("org.assertj:assertj-core:3.22.0")
    api("org.opensaml:opensaml:2.6.4")
    api("org.reflections:reflections:0.9.11") { version { reject("[0.9.12,)") } } // Upgrade breaks 'com.github.racc:typesafeconfig-guice'
}

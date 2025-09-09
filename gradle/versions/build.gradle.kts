plugins {
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
    id("org.gradle.java-platform")
    id("org.example.gradle.check.format-gradle")
}

val assertj = "3.22.0"
val guava = "33.4.0-jre"
val guice = "5.1.0"
val httpcomponents = "4.5.14"
val jackson = "2.13.5"
val jakartaActivation = "1.2.2"
val jakartaInject = "1.0.5"
val jakartaMail = "1.6.7"
val jakartaServlet = "4.0.4"
val jsr305 = "3.0.2"
val junit5 = "5.8.2"
val opensaml = "2.6.4"
val orgJson = "20250107"
val poi = "5.4.0"
val reflections = "0.9.11"
val resteasy = "4.7.6.Final"
val slf4j = "1.7.36"
val solrj = "7.7.3"
val typesafeconfig = "0.1.0"
val velocity = "2.4.1"
val zookeeper = "3.9.3"

// Reject versions that should not be upgraded beyond a certain point.
// This makes Dependabot PR builds fail that attempt to update these.
dependencies.constraints {
    api("com.fasterxml.jackson.core:jackson-annotations:$jackson")
    api("com.fasterxml.jackson.core:jackson-core:$jackson")
    api("com.github.racc:typesafeconfig-guice:$typesafeconfig")
    api("com.google.code.findbugs:jsr305:$jsr305")
    api("com.google.guava:guava:$guava")
    api("com.google.inject:guice:$guice")
    api("com.sun.activation:jakarta.activation:$jakartaActivation") { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api("com.sun.mail:jakarta.mail:$jakartaMail") { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api("jakarta.inject:jakarta.inject-api:$jakartaInject") { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api("jakarta.servlet:jakarta.servlet-api:$jakartaServlet") { version { reject("[5.0.0,)") } } // Stay Tomcat 8 compatible
    api("org.apache.httpcomponents:fluent-hc:$httpcomponents")
    api("org.apache.poi:poi:$poi")
    api("org.apache.solr:solr-solrj:$solrj") { version { reject("[8.0.0,)") } } // API changes in 8 require production code changes
    api("org.apache.velocity:velocity-engine-core:$velocity")
    api("org.apache.zookeeper:zookeeper:$zookeeper")
    api("org.assertj:assertj-core:$assertj")
    api("org.jboss.resteasy:resteasy-core:$resteasy") { version { reject("[5.0.0.Final,)") } }
    api("org.json:json:$orgJson")
    api("org.junit.jupiter:junit-jupiter-api:$junit5")
    api("org.opensaml:opensaml:$opensaml")
    api("org.reflections:reflections:$reflections") { version { reject("[0.9.12,)") } } // Upgrade breaks 'com.github.racc:typesafeconfig-guice'
    api("org.slf4j:slf4j-api:$slf4j")
    api("org.slf4j:slf4j-simple:$slf4j")
}

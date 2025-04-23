plugins {
    id("org.gradle.java")
    id("org.springframework.boot")
    id("org.example.gradle.base.dependency-rules")
}

dependencies { developmentOnly("org.springframework.boot:spring-boot-devtools") }

configurations {
    productionRuntimeClasspath {
        extendsFrom(configurations["internal"])
        shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"])
    }
    developmentOnly {
        extendsFrom(configurations["internal"])
        shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"])
    }
}

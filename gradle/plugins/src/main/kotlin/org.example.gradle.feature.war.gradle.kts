plugins {
    id("org.gradle.war")
    id("org.example.gradle.base.dependency-rules")
}

// The war plugin used 'providedRuntime' / 'providedCompile' to resolve dependencies for packaging
// the WAR file
configurations.providedCompile { shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"]) }

configurations.providedRuntime { shouldResolveConsistentlyWith(configurations["mainRuntimeClasspath"]) }

tasks.register<Copy>("deployWebApp") {
    group = "distribution"
    description = "Deploy web app into local Tomcat found via CATALINA_HOME"
    from(tasks.war) { into("webapps") }
    into(providers.gradleProperty("catalinaHome").orElse(providers.environmentVariable("CATALINA_HOME")))
}

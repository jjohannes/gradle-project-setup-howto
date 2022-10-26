plugins {
    id("org.gradlex.build-parameters") version "1.4.3"
}

group = "org.example"

buildParameters {
    pluginId("org.example.build-parameters")
    bool("ci") {
        defaultValue.set(false)
        fromEnvironment()
    }
    group("catalina") {
        string("home") {
            fromEnvironment()
            description.set("Root of the Tomcat installation")
        }
    }
}

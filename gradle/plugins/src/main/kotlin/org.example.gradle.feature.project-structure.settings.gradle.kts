import org.gradlex.javamodule.dependencies.initialization.JavaModulesExtension

plugins { id("org.gradlex.java-module-dependencies") }

configure<JavaModulesExtension> {
    directory(".") {
        // Set the group required to refer to a Module "from outside".
        // I.e., when it is published or used in Included Builds.
        group = "org.example.product.javamodules"
    }
    versions("gradle/versions")

    // Analysis project to create reports about the whole software (coverage, SBOM, ...)
    module("gradle/aggregation")
}

// Allow local projects to be referred to by accessor
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

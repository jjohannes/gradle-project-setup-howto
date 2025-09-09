plugins { id("org.gradlex.jvm-dependency-conflict-resolution") }

jvmDependencyConflicts {
    // Configure build wide consistent resolution. That is, the versions that are used on the
    // runtime classpath of the  web applications should also be used in all other places
    // (e.g. also when compiling a project at the bottom of the dependency graph that does not
    // see most of the other dependencies that may influence the version choices).
    consistentResolution {
        platform(":versions")
        providesVersions(":aggregation")
    }

    // Configure logging capabilities plugin to default to Slf4JSimple
    logging { enforceSlf4JSimple() }

    patch {
        // Avoid conflict between 'javax.activation' and 'jakarta.activation-api' in the
        // detachedConfiguration that is input to AndroidLintTask.lintTool.classpath
        // Because for 'detachedConfigurations' you cannot inject conflict resolution strategies
        //   select(JAVAX_ACTIVATION_API, "jakarta.activation:jakarta.activation-api")
        // would not solve the issue.
        module("com.android.tools:repository") {
            removeDependency("com.sun.activation:javax.activation")
            addApiDependency("jakarta.activation:jakarta.activation-api")
        }

        module("com.github.racc:typesafeconfig-guice") {
            // remove and re-add due to 'no_aop' classifier
            removeDependency("com.google.inject:guice")
            addApiDependency("com.google.inject:guice")
        }

        module("com.google.guava:guava") {
            // remove annotation libraries we do not need
            removeDependency("com.google.j2objc:j2objc-annotations")
            removeDependency("org.checkerframework:checker-qual")
        }

        align(
            "org.apache.httpcomponents:httpclient",
            "org.apache.httpcomponents:httpmime",
            "org.apache.httpcomponents:fluent-hc",
            "org.apache.httpcomponents:httpclient-cache",
            "org.apache.httpcomponents:httpclient-win",
            "org.apache.httpcomponents:httpclient-osgi",
        )
        align(
            "org.apache.poi:poi",
            "org.apache.poi:poi-excelant",
            "org.apache.poi:poi-ooxml",
            "org.apache.poi:poi-scratchpad",
        )
        align("com.google.inject.extensions:guice-servlet", "com.google.inject:guice")
        align(
            "org.jboss.resteasy:resteasy-core",
            "org.jboss.resteasy:resteasy-guice",
            "org.jboss.resteasy:resteasy-jackson2-provider",
        )
    }
}

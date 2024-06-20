plugins {
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.feature.use-all-catalog-versions")
    id("org.example.gradle.check.format-gradle")
}

// Reject versions that should not be upgraded beyond a certain point.
// This makes Dependabot PR builds fail that attempt to update these.
dependencies.constraints {
    api(libs.jakarta.activation) { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api(libs.jakarta.inject.api) { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api(libs.jakarta.mail.impl) { version { reject("[2.0.0,)") } } // Upgrade to 2.x requires newer Jakarta APIs
    api(libs.jakarta.servlet.api) { version { reject("[5.0.0,)") } } // Stay Tomcat 8 compatible
    api(libs.org.reflections) { version { reject("[0.9.12,)") } } // Upgrade breaks 'com.github.racc:typesafeconfig-guice'
    api(libs.resteasy.core) { version { reject("[5.0.0.Final,)") } }
    api(libs.solr.solrj) { version { reject("[8.0.0,)") } } // API changes in 8 require production code changes
}

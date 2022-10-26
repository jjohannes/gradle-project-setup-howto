import buildparameters.BuildParametersExtension

plugins {
    // Use the Gradle Enterprise plugin to publish Build Scan to https://scans.gradle.com
    id("com.gradle.enterprise")
    id("org.example.build-parameters")
}

// Configure the Gradle Enterprise plugin
gradleEnterprise {
    buildScan {
        // You may remove this check to publish build scans for every build (preferred, if there are no concerns)
        if (the<BuildParametersExtension>().ci) {
            publishAlways()
        }
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

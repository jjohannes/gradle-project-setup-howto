plugins { id("com.gradle.develocity") }

// Configure Build Scans (local builds have to opt-in via --scan)
develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
        termsOfUseAgree = "yes"
        if (!providers.environmentVariable("CI").getOrElse("false").toBoolean()) {
            publishing.onlyIf { false } // only publish with explicit '--scan'
        }
    }
}

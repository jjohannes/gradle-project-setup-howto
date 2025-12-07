import com.android.build.api.attributes.BuildTypeAttr

plugins {
    id("org.gradle.java")
    id("org.example.gradle.base.lifecycle")
    id("org.example.gradle.base.dependency-rules")
    id("org.example.gradle.check.format-base")
    id("org.example.gradle.check.format-gradle")
    // id("org.example.gradle.report.code-coverage")
    id("org.example.gradle.report.plugin-analysis")
    id("org.example.gradle.report.sbom")
    // id("org.example.gradle.report.test")
}

dependencies { implementation(projects.appJamcatch) }

configurations {
    compileClasspath { selectRelease() }
    runtimeClasspath { selectRelease() }
    testCompileClasspath { selectRelease() }
    testRuntimeClasspath { selectRelease() }
}

fun Configuration.selectRelease() {
    attributes.attribute(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE, "jar")
    attributes.attribute(BuildTypeAttr.ATTRIBUTE, objects.named("release"))
}

// Allow AARs to appear along JARs on the joint classpath for reports
dependencies {
    attributesSchema { getMatchingStrategy(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE).compatibilityRules.add(AarJarCompatibility::class.java) }
    attributesSchema { getMatchingStrategy(ArtifactTypeDefinition.ARTIFACT_TYPE_ATTRIBUTE).compatibilityRules.add(AarJarStringCompatibility::class.java) }
}

class AarJarCompatibility : AttributeCompatibilityRule<LibraryElements> {
    override fun execute(details: CompatibilityCheckDetails<LibraryElements>) {
        if (details.producerValue?.name == "aar") {
            details.compatible()
        }
    }
}

class AarJarStringCompatibility : AttributeCompatibilityRule<String> {
    override fun execute(details: CompatibilityCheckDetails<String>) {
        if (details.producerValue == "aar" || details.producerValue == "android-classes-jar") {
            details.compatible()
        }
    }
}

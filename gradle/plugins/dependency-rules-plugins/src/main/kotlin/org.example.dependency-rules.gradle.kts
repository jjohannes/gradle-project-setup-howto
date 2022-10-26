import org.example.metadatarules.fixdependencies.TypesafeConfigGuiceRule
import org.example.metadatarules.status.ComponentStatusRule
import org.example.metadatarules.versionalignment.HttpComponentsPlatformRule
import org.example.metadatarules.versionalignment.PoiPlatformRule
import org.example.metadatarules.versionalignment.Slf4jPlatformRule
import org.example.metadatarules.versionalignment.ParentPomAsPlatformRule

plugins {
    id("dev.jacomet.logging-capabilities")
    id("org.gradlex.java-ecosystem-capabilities")
}

// Configure logging capabilities plugin to default to Slf4JSimple
loggingCapabilities.enforceSlf4JSimple()

dependencies.components {
    // Versions that are not final get the 'integration' status
    all<ComponentStatusRule>()

    // Fix dependencies
    withModule<TypesafeConfigGuiceRule>(TypesafeConfigGuiceRule.TYPESAFE_CONFIG_GUICE_MODULE)

    // Define 'Alignment Platforms' (platforms for multi-component libraries without published BOM)
    withModule<HttpComponentsPlatformRule>(HttpComponentsPlatformRule.HTTP_COMPONENTS_CLIENT)
    withModule<PoiPlatformRule>(PoiPlatformRule.POI_COMMON)
    withModule<Slf4jPlatformRule>(Slf4jPlatformRule.SLF4J_PARENT)

    // Make parents usable as pure 'Alignment Platforms' - remove all constraints that do not concern the alignment
    withModule<ParentPomAsPlatformRule>(HttpComponentsPlatformRule.HTTP_COMPONENTS_CLIENT)
    withModule<ParentPomAsPlatformRule>(Slf4jPlatformRule.SLF4J_PARENT)
}

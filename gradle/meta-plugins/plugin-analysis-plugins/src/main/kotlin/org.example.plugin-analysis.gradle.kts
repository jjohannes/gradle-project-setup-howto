import org.example.pluginanalysis.PluginApplicationOrderAnalysis

tasks.register<PluginApplicationOrderAnalysis>("analysePluginApplicationOrder") {
    group = "help"

    pluginSrcFolders.from(subprojects.map { it.layout.projectDirectory.dir("src/main/kotlin") })
    pluginApplicationDiagram.set(layout.buildDirectory.file("reports/plugins/plugin-application-order.puml"))
}
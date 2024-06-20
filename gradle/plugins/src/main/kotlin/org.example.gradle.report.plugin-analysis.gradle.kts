import org.example.gradle.tasks.PluginApplicationOrderAnalysis

// Parse '*.gradle.kts' files and create a PlantUML diagram based on the application order
tasks.register<PluginApplicationOrderAnalysis>("analysePluginApplicationOrder") {
    group = "help"

    pluginSrcFolders.from(project.layout.projectDirectory.dir("../plugins/src/main/kotlin"))
    pluginApplicationDiagram = layout.buildDirectory.file("reports/plugins/plugin-application-order.puml")
}

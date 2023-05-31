package org.example.android.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 * Encodes the current product version in an XML format used at application runtime.
 */
abstract class VersionXml : DefaultTask() {

    @get:Input
    abstract val mainVersion: Property<String>

    @get:OutputFile
    abstract val xmlFile: RegularFileProperty

    @TaskAction
    fun generate() {
        xmlFile.get().asFile.writeText(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?><exampleApp version=\"${mainVersion.get()}\"></exampleApp>"
        )
    }
}

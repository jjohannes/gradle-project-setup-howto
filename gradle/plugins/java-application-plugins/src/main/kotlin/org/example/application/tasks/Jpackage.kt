package org.example.application.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.toolchain.JavaInstallationMetadata
import org.gradle.process.ExecOperations
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.inject.Inject


@CacheableTask
abstract class Jpackage : DefaultTask() {
    
    @get:Nested
    abstract val javaInstallation: Property<JavaInstallationMetadata>

    @get:Input
    abstract val operatingSystem: Property<String>

    @get:Input
    abstract val architecture: Property<String>

    @get:Input
    abstract val mainModule: Property<String>

    @get:Input
    abstract val mainClass: Property<String>

    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:InputDirectory
    abstract val resources: DirectoryProperty

    @get:Input
    abstract val version: Property<String?>

    @get:Classpath
    abstract val modulePath: ConfigurableFileCollection

    @get:Input
    abstract val javaOptions: ListProperty<String?>

    @get:Input
    abstract val applicationName: Property<String>

    @get:Input
    abstract val applicationDescription: Property<String?>

    @get:Input
    abstract val vendor: Property<String?>

    @get:Input
    abstract val copyright: Property<String?>

    @get:OutputDirectory
    abstract val destination: DirectoryProperty

    @get:Inject
    protected abstract val files: FileOperations

    @get:Inject
    protected abstract val exec: ExecOperations

    @TaskAction
    @Throws(NoSuchAlgorithmException::class, IOException::class)
    fun runJlink() {
        files.delete(destination)

        val os = operatingSystem.get()
        val arch = architecture.get()
        val hostOs = System.getProperty("os.name").replace(" ", "").lowercase()
        val hostArch = System.getProperty("os.arch")

        val resourcesDir = destination.dir("additional-resources").get()

        if (arch.contains("64") && !hostArch.contains("64")) {
            wrongHostSystemError(hostArch, arch)
        }
        if (arch.contains("aarch") && !hostArch.contains("aarch")) {
            wrongHostSystemError(hostArch, arch)
        }
        if (!arch.contains("aarch") && hostArch.contains("aarch")) {
            wrongHostSystemError(hostArch, arch)
        }

        if (os.contains("macos")) {
            if (!hostOs.contains(os)) {
                wrongHostSystemError(hostOs, os)
            }
            val icon = resources.file("icon.icns").get()
            files.copy {
                from(icon) {
                    rename { "${applicationName.get()}.icns" }
                }
                from(icon) {
                    rename { "${applicationName.get()}-volume.icns" }
                }
                into(resourcesDir)
            }
        } else if (os.contains("windows")) {
            if (!hostOs.contains(os)) {
                wrongHostSystemError(hostOs, os)
            }
            val icon = resources.file("icon.ico").get()
            files.copy {
                from(icon) {
                    rename { "${applicationName.get()}.ico" }
                }
                into(resourcesDir)
            }
        } else {
            if (hostOs.contains("windows") || hostOs.contains("osx")) {
                wrongHostSystemError(hostOs, os)
            }
            val icon = resources.file("icon.png").get()
            files.copy {
                from(icon) {
                    rename { "${applicationName.get()}.png" }
                }
                into(resourcesDir)
            }
        }

        val dest = destination.get().asFile

        exec.exec {
            commandLine(
                javaInstallation.get().getInstallationPath().file("bin/jpackage").getAsFile().getAbsolutePath(),
                "--module",
                mainModule.get() + "/" + mainClass.get(),
                "--resource-dir",
                resourcesDir.asFile.absolutePath,
                "--app-version",
                version.get(),
                "--module-path",
                modulePath.getAsPath(),
                "--name",
                applicationName.get(),
                "--vendor",
                vendor.get(),
                "--copyright",
                copyright.get(),
                "--description",
                applicationDescription.get(),
                "--dest",
                dest.path
            )
            for (javaOption in javaOptions.get()) {
                args("--java-options", javaOption)
            }
        }

        files.delete(resourcesDir)

        for (result in Objects.requireNonNull(dest.listFiles())) {
            val digest = MessageDigest.getInstance("SHA-256")
            val encoded = digest.digest(Files.readAllBytes(result.toPath()))
            Files.write(File(dest, result.name + ".sha256").toPath(), bytesToHex(encoded).toByteArray())
        }
    }

    private fun bytesToHex(hash: ByteArray): String {
        val hexString = StringBuilder(2 * hash.size)
        for (b in hash) {
            val hex = Integer.toHexString(0xff and b.toInt())
            if (hex.length == 1) {
                hexString.append('0')
            }
            hexString.append(hex)
        }
        return hexString.toString()
    }

    private fun wrongHostSystemError(hostOs: String, os: String) {
        throw RuntimeException("Running on $hostOs; cannot build for $os")
    }
}

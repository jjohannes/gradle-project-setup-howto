package org.example.application.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files
import java.security.DigestInputStream
import java.security.MessageDigest

/**
 * Gradle task based on 'Checksum' Ant Task but stripped down to what we need in this build.
 *
 * See: https://github.com/apache/ant/blob/master/src/main/org/apache/tools/ant/taskdefs/Checksum.java
 */
abstract class MD5DirectoryChecksum : DefaultTask() {

    @get:InputDirectory
    abstract val inputDirectory: DirectoryProperty

    @get:OutputFile
    abstract val checksumFile: RegularFileProperty

    @TaskAction
    fun generateChecksum() {
        val messageDigest = MessageDigest.getInstance("MD5")

        val allDigests = mutableMapOf<File, ByteArray>()
        val bufSize = 8 * 1024
        val buf = ByteArray(bufSize)

        val folder = inputDirectory.get().asFile
        folder.walkTopDown().filter { it.isFile }.forEach {
            messageDigest.reset()
            val fis = Files.newInputStream(it.toPath())
            val dis = DigestInputStream(
                fis,
                messageDigest
            )
            while (dis.read(buf, 0, bufSize) != -1) {
                // Empty statement
            }
            dis.close()
            fis.close()
            val fileDigest = messageDigest.digest()
            allDigests[it] = fileDigest
        }
        // Calculate the total checksum
        // Convert the keys (source files) into a sorted array.
        val keyArray = allDigests.keys.sortedBy { it.relativeTo(folder).path  }

        // Loop over the checksums and generate a total hash.
        messageDigest.reset()
        keyArray.forEach {
            // Add the digest for the file content
            val digest = allDigests.getValue(it)
            messageDigest.update(digest)

            // Add the file path
            val fileName = it.relativeTo(folder).path.replace(File.separatorChar, '/')
            messageDigest.update(fileName.toByteArray())
        }
        checksumFile.get().asFile.writeText(createDigestString(messageDigest.digest()))
    }

    private fun createDigestString(fileDigest: ByteArray): String {
        val byteMask = 0xFF
        val checksumSb = StringBuilder()
        for (digestByte in fileDigest) {
            checksumSb.append(String.format("%02x", byteMask and digestByte.toInt()))
        }
        return checksumSb.toString()
    }
}
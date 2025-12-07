package org.example.jamcatch.actors.end2end

import org.example.javarca.engine.test.fixtures.RendererFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JamCatchActorsEnd2EndTest {
    @Test
    fun test() {
        val result = RendererFixture.launchAndWaitForFinish()

        Assertions.assertTrue(result.file.exists(), "Screenshot should exist")
    }
}

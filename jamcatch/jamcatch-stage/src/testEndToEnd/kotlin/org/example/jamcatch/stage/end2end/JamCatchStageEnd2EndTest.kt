package org.example.jamcatch.stage.end2end

import org.example.javarca.engine.test.fixtures.RendererFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("slow")
class JamCatchStageEnd2EndTest {
    @Test
    fun test() {
        val result = RendererFixture.launchAndWaitForFinish()

        Assertions.assertTrue(result.file.exists(), "Screenshot should exist")
    }
}

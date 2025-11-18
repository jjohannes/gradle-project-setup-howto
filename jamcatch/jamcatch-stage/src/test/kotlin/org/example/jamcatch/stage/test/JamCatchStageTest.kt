package org.example.jamcatch.stage.test

import org.example.jamcatch.stage.JamCatchStage
import org.example.javarca.model.Stage
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JamCatchStageTest {
    @Test
    fun testStageDefinition() {
        // Arrange
        val stage: Stage = JamCatchStage()

        val definition = stage.define()

        Assertions.assertNotNull(definition)
        Assertions.assertTrue(definition.contains("p"), "Stage should contain player character")
        Assertions.assertTrue(definition.contains("J"), "Stage should contain jar character")
        Assertions.assertTrue(definition.contains("X"), "Stage should contain solid blocks")
        Assertions.assertTrue(definition.contains("x"), "Stage should contain wall blocks")
    }
}

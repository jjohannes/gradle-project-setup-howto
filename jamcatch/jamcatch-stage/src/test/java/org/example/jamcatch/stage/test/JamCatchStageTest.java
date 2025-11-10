package org.example.jamcatch.stage.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.jamcatch.stage.JamCatchStage;
import org.example.javarca.model.Stage;
import org.junit.jupiter.api.Test;

public class JamCatchStageTest {

    @Test
    public void testStageDefinition() {
        // Arrange
        Stage stage = new JamCatchStage();

        String definition = stage.define();

        assertNotNull(definition);
        assertTrue(definition.contains("p"), "Stage should contain player character");
        assertTrue(definition.contains("J"), "Stage should contain jar character");
        assertTrue(definition.contains("X"), "Stage should contain solid blocks");
        assertTrue(definition.contains("x"), "Stage should contain wall blocks");
    }
}

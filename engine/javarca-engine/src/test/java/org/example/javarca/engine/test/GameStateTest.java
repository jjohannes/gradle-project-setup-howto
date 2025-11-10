package org.example.javarca.engine.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.example.javarca.engine.GameState;
import org.example.javarca.engine.Spot;
import org.junit.jupiter.api.Test;

public class GameStateTest {

    @Test
    public void testGameStateInitialization() {
        GameState gameState = new GameState();

        assertNotNull(gameState);
        List<Spot> spots = gameState.getSpots();
        assertNotNull(spots);
        assertFalse(spots.isEmpty(), "Spots list should not be empty");
        assertNotNull(gameState.getAll(), "Actor states should not be null");
        assertNotNull(gameState.getImages(), "Images map should not be null");
    }

    @Test
    public void testDirectionFlags() {
        GameState gameState = new GameState();

        assertFalse(gameState.isUp(), "Up flag should be false initially");
        assertFalse(gameState.isDown(), "Down flag should be false initially");
        assertFalse(gameState.isLeft(), "Left flag should be false initially");
        assertFalse(gameState.isRight(), "Right flag should be false initially");

        gameState.setUp(true);
        gameState.setDown(true);
        gameState.setLeft(true);
        gameState.setRight(true);

        assert (gameState.isUp());
        assert (gameState.isDown());
        assert (gameState.isLeft());
        assert (gameState.isRight());
    }
}

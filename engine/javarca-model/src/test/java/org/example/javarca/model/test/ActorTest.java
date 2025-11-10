package org.example.javarca.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.example.javarca.model.Actor;
import org.example.javarca.model.ActorCollision;
import org.example.javarca.model.ActorPropertyModifier;
import org.junit.jupiter.api.Test;

public class ActorTest {

    @Test
    public void testActorCreation() {
        // Arrange
        char symbol = 'A';
        Set<ActorPropertyModifier> modifiers = new HashSet<>();
        Map<Character, ActorCollision> collisionFunctions = new HashMap<>();

        Actor actor = new Actor(symbol, modifiers, collisionFunctions);

        assertEquals(symbol, actor.symbol());
        assertEquals(modifiers, actor.modifiers());
        assertEquals(collisionFunctions, actor.collisionFunctions());
    }
}

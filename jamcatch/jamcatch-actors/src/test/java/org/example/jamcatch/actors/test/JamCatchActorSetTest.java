package org.example.jamcatch.actors.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;
import org.example.jamcatch.actors.JamCatchActorSet;
import org.example.javarca.model.Actor;
import org.example.javarca.model.ActorSet;
import org.junit.jupiter.api.Test;

public class JamCatchActorSetTest {

    @Test
    public void testActorSetCreation() {
        ActorSet actorSet = new JamCatchActorSet();

        assertNotNull(actorSet);
        Set<Actor> actors = actorSet.items();
        assertNotNull(actors);
        assertFalse(!actors.isEmpty(), "Actor set should not be empty");
    }
}

package org.example.jamcatch.actors.test

import org.example.jamcatch.actors.JamCatchActorSet
import org.example.javarca.model.ActorSet
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JamCatchActorSetTest {
    @Test
    fun testActorSetCreation() {
        val actorSet: ActorSet = JamCatchActorSet()

        Assertions.assertNotNull(actorSet)
        val actors = actorSet.items()
        Assertions.assertNotNull(actors)
        Assertions.assertFalse(actors.isEmpty(), "Actor set should not be empty")
    }
}

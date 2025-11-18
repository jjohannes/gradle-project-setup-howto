package org.example.jamcatch.actors.collisions

import java.util.Random
import java.util.function.Predicate
import org.example.javarca.model.*

interface Collisions {
    companion object {
        val DEMO_MODE: String? = System.getenv("DEMO_MODE")

        val p: Map<Char, ActorCollision> =
            mapOf(
                'J' to
                    ActorCollision {
                        myState: ActorState,
                        otherState: ActorState,
                        allStates: ActorStates ->
                        otherState.destroy()
                        myState.addToValue(
                            ActorProperty.POINTS,
                            otherState.getValue(ActorProperty.POINTS),
                        )
                        allStates.filter('0').print(myState.getValue(ActorProperty.POINTS))
                        var skin = 'H'
                        skin += Random().nextInt(5).toChar().code
                        val newJar = allStates.spawn('J', Random().nextInt(14) + 1, 0, skin)
                        newJar.setValue(
                            ActorProperty.SPEEDY,
                            otherState.getValue(ActorProperty.SPEEDY) + 100,
                        )
                    },
                GameConstants.SYMBOL_EMPTY_SPOT to
                    ActorCollision {
                        myState: ActorState,
                        otherState: ActorState,
                        allStates: ActorStates ->
                        allStates
                            .filter(':')
                            .setSkin(GameConstants.SYMBOL_EMPTY_SPOT) // make text row invisible
                        val target =
                            allStates
                                .filter('J')
                                .filter(ActorProperty.SPEEDY) { v: Int -> v > 0 }
                                .getMaxX()
                        if (DEMO_MODE != null) {
                            if (myState.getX() > target) {
                                myState.setX(myState.getX() - 1500)
                            } else if (myState.getX() < target) {
                                myState.setX(myState.getX() + 1500)
                            }
                        }
                    },
            )
        val j: Map<Char, ActorCollision> =
            mapOf(
                'J' to
                    ActorCollision {
                        myState: ActorState,
                        otherState: ActorState,
                        allStates: ActorStates ->
                        myState.setValue(ActorProperty.SPEEDY, 0)
                        val player = allStates.filter('p')
                        player.setY(
                            allStates
                                .filter('J')
                                .filter(ActorProperty.SPEEDY, Predicate { v: Int? -> v == 0 })
                                .getMinY() - GameConstants.PRECISION
                        )
                        if (player.getMinY() <= GameConstants.PRECISION * 2) {
                            player.destroy()
                            allStates.filter(':').print("GAME.OVER")
                        } else {
                            allStates.spawn('J', Random().nextInt(14) + 1, 0)
                        }
                    },
                'X' to
                    ActorCollision {
                        myState: ActorState,
                        otherState: ActorState,
                        allStates: ActorStates ->
                        myState.setValue(ActorProperty.SPEEDY, 0)
                        allStates.spawn('J', Random().nextInt(14) + 1, 0)
                        allStates
                            .filter('p')
                            .setY(
                                allStates
                                    .filter('J')
                                    .filter(ActorProperty.SPEEDY, Predicate { v: Int? -> v == 0 })
                                    .getMinY() - GameConstants.PRECISION
                            )
                    },
            )

        val ALL: Map<Char, Map<Char, ActorCollision>> =
            mapOf<Char, Map<Char, ActorCollision>>('p' to p, 'J' to j)
    }
}

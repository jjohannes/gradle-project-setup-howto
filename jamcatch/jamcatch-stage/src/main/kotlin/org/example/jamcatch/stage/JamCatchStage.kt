package org.example.jamcatch.stage

import org.example.javarca.model.Stage
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class JamCatchStage : Stage {

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(JamCatchStage::class.java)
    }

    override fun define(): String {
        LOG.debug("Constructing stage for JamCatch")
        return """
        x . . J . . . . . . 0 0 0 0 0 x
        x . . . . . . . . . . . . . . x
        x . . : : : : : : : : : . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . . . . . . . . . . . x
        x . . . . p . . . . . . . . . x
        x X X X X X X X X X X X X X X x          
        """
            .trimIndent()
    }
}

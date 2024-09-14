package org.example.product.coruscant

import java.io.BufferedReader
import java.io.InputStreamReader
import org.slf4j.LoggerFactory

/** I am published and therefore I have Javadoc! */
class CoruscantModule {
    /** Does fancy stuff. */
    init {
        LOGGER.info("Coruscant Module created")
        CoruscantModule::class.java.getResourceAsStream("hello.txt").use { hello ->
            println(BufferedReader(InputStreamReader(hello)).readLine())
        }
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CoruscantModule::class.java)
    }
}

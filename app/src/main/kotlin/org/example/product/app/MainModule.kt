package org.example.product.app

import com.google.inject.Guice
import com.google.inject.servlet.SessionScoped
import org.example.product.bespin.BespinModule
import org.example.product.kamino.KaminoModule
import org.example.product.kashyyyk.KashyyykModule
import org.example.product.naboo.NabooModule
import org.example.product.tatooine.TatooineModule
import org.slf4j.LoggerFactory

@SessionScoped
class MainModule {

    fun run() {
        LOGGER.info("Running application...")

        BespinModule().doThings()
        KaminoModule()
        KashyyykModule()
        NabooModule()
        TatooineModule()

        Guice.createInjector()
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(MainModule::class.java)
    }
}

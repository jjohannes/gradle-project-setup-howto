package org.example.product.kamino

import org.example.product.coruscant.CoruscantModuleData
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap

/** I am published and therefore I have Javadoc! */
class KaminoModule {
    /** Bootstrap... */
    var bootstrap: ResteasyBootstrap? = null

    /** Data... */
    var data: CoruscantModuleData? = null

    /**
     * Info!
     *
     * @return all the important Classes
     */
    fun info(): Array<Class<*>> {
        return arrayOf(
            ResteasyBootstrap::class.java,
            ResteasyJackson2Provider::class.java,
            GuiceResteasyBootstrapServletContextListener::class.java
        )
    }
}

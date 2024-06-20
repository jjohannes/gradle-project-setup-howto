package org.example.product.kamino;

import org.example.product.coruscant.CoruscantModuleData;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;

/**
 * I am published and therefore I have Javadoc!
 */
public class KaminoModule {

    /**
     * Bootstrap...
     */
    public ResteasyBootstrap bootstrap;

    /**
     * Data...
     */
    public CoruscantModuleData data;

    /**
     * Info!
     *
     * @return all the important Classes
     */
    public Class<?>[] info() {
        return new Class<?>[] {
            ResteasyBootstrap.class, ResteasyJackson2Provider.class, GuiceResteasyBootstrapServletContextListener.class
        };
    }
}

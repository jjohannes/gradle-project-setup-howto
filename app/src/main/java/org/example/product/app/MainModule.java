package org.example.product.app;

import org.example.product.bespin.BespinModule;
import org.example.product.kamino.KaminoModule;
import org.example.product.kashyyyk.KashyyykModule;
import org.example.product.naboo.NabooModule;
import org.example.product.tatooine.TatooineModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainModule.class);

    public void run() throws IOException {
        LOGGER.info("Running application...");

        new BespinModule().doThings();
        new KaminoModule();
        new KashyyykModule();
        new NabooModule();
        new TatooineModule();
    }
}

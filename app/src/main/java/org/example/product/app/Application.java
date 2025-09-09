package org.example.product.app;

import java.io.IOException;

public class Application {

    private Application() {}

    public static void main(String[] args) throws IOException {
        new MainModule().run();
    }
}

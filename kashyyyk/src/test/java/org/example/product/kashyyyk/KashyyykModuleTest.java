package org.example.product.kashyyyk;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;

public class KashyyykModuleTest {

    @Test
    void testModule() throws IOException {
        KashyyykModule module = new KashyyykModule();
        assertSame(5, module.calculateSomethingImportant());

        try (InputStream hello = KashyyykModuleTest.class.getResourceAsStream("testHello.txt")) {
            String message = new BufferedReader(new InputStreamReader(hello)).readLine();
            assertEquals("TEST HELLO!", message);
        }
    }
}

package org.example.product.kamino.test;

import org.example.product.kamino.KaminoModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class KaminoModuleTest {

    @Test
    void testModule() {
        new KaminoModule();
        assertSame(4, 2 + 2);
    }
}

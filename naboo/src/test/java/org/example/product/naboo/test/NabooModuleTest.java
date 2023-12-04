package org.example.product.naboo.test;

import org.example.product.naboo.NabooModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class NabooModuleTest {

    @Test
    void testModule() {
        new NabooModule();
        assertSame(5, 1 + 2 + 2);
    }
}

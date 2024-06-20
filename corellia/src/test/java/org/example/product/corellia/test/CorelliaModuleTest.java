package org.example.product.corellia.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.product.corellia.fixtures.CorelliaModuleFixture;
import org.junit.jupiter.api.Test;

public class CorelliaModuleTest {

    @Test
    void testModule() {
        assertEquals(3, new CorelliaModuleFixture().threeModules().size());
    }
}

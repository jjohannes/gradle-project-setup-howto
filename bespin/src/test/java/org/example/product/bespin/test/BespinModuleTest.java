package org.example.product.bespin.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.product.corellia.fixtures.CorelliaModuleFixture;
import org.junit.jupiter.api.Test;

public class BespinModuleTest {

    @Test
    void testModule() {
        assertEquals(3, new CorelliaModuleFixture().threeModules().size());
    }
}

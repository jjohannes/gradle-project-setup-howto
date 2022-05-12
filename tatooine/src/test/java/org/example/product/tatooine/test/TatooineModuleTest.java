package org.example.product.tatooine.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class TatooineModuleTest {

    @Test
    public void testModule() {
        assertSame("Junit4", "Junit" + (2 + 2));
    }
}

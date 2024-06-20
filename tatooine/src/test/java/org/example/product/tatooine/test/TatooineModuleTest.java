package org.example.product.tatooine.test;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TatooineModuleTest {

    @Test
    public void testModule() {
        assertSame("Junit4", "Junit" + (2 + 2));
    }
}

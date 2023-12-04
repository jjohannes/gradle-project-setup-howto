package org.example.product.tatooine.test;

import org.example.product.tatooine.TatooineModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class TatooineModuleTest {

    @Test
    public void testModule() {
        new TatooineModule();
        assertSame("Junit4", "Junit" + (2 + 2));
    }
}

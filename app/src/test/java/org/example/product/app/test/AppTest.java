package org.example.product.app.test;

import org.example.product.app.Application;
import org.example.product.app.MainModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class AppTest {

    @Test
    void testModule() {
        new MainModule();
        assertSame(4, 2 + 2);
    }
}

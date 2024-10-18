package org.example.product.tatooine.test

import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test

class TatooineModuleTest {
    @Test
    fun testModule() {
        assertSame("Junit4", "Junit" + (2 + 2))
    }
}

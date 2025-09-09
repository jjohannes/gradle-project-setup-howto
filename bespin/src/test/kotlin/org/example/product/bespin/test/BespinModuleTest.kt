package org.example.product.bespin.test

import org.example.product.corellia.fixtures.CorelliaModuleFixture
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BespinModuleTest {
    @Test
    fun testModule() {
        Assertions.assertEquals(3, CorelliaModuleFixture().threeModules().size)
    }
}

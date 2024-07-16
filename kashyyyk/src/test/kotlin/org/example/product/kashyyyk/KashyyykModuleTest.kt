package org.example.product.kashyyyk

import java.io.BufferedReader
import java.io.InputStreamReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class KashyyykModuleTest {

    @Test
    fun testModule() {
        val module = KashyyykModule()
        Assertions.assertSame(5, module.calculateSomethingImportant())
        KashyyykModuleTest::class.java.getResourceAsStream("testHello.txt").use { hello ->
            val message = BufferedReader(InputStreamReader(hello)).readLine()
            Assertions.assertEquals("TEST HELLO!", message)
        }
    }
}

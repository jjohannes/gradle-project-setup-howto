package org.example.product.app.endtoend

import com.google.common.collect.ImmutableList
import org.example.product.app.mock.api.MockServer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SystemQuickTest {
    @Test
    fun testSome() {
        Assertions.assertEquals(0, sendStuff(MockServer().get()))
    }

    private fun sendStuff(things: ImmutableList<String>): Int {
        return 0
    }
}

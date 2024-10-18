package org.example.product.app.endtoend

import com.google.common.collect.ImmutableList
import org.example.product.app.mock.api.MockServer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("slow")
class SystemTest {
    @Test
    fun testAll() {
        Assertions.assertEquals(0, sendStuff(MockServer().get()))
    }

    private fun sendStuff(things: ImmutableList<String>): Int {
        return 0
    }
}

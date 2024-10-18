package org.example.product.app.mock.api

import com.google.common.collect.ImmutableList
import org.example.product.app.MainModule

class MockServer {
    fun get(): ImmutableList<String> {
        MainModule()
        return ImmutableList.of("a", "b", "x", "y")
    }
}

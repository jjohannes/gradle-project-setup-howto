package org.example.product.kashyyyk

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class KashyyykModule {
    init {
        try {
            KashyyykModule::class.java.getResourceAsStream("hello.txt").use { hello ->
                val message = BufferedReader(InputStreamReader(hello)).readLine()
                println(message)
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun calculateSomethingImportant(): Int {
        return 4 + 1
    }
}

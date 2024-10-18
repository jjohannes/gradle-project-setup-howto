package org.example.product.kashyyyk

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import org.example.product.bespin.BespinModule
import org.example.product.kamino.KaminoModule
import org.example.product.naboo.NabooModule
import org.example.product.tatooine.TatooineModule

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

    fun flyTo(from: TatooineModule?): NabooModule {
        KaminoModule()
        BespinModule()
        val a = calculateSomethingImportant()
        return NabooModule(a)
    }

    fun calculateSomethingImportant(): Int {
        return 4 + 1
    }
}

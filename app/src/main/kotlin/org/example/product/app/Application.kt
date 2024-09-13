package org.example.product.app

object Application {
    fun main(args: Array<String>) {
        MainModule().run(args.toList())
    }
}

fun main(args: Array<String>) {
    Application.main(args)
}

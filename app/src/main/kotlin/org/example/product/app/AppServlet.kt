package org.example.product.app

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppServlet {

    @GetMapping("/")
    fun index(): String {
        MainModule().run()
        val version =
            BufferedReader(
                    InputStreamReader(
                        Objects.requireNonNull(
                            AppServlet::class.java.getResourceAsStream("/version.txt")
                        )
                    )
                )
                .readLine()
        return "<html><body>App is running... !!!!<br/>Version $version</body></html>"
    }
}

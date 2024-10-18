package org.example.product.tatooine

import com.github.racc.tscg.TypesafeConfig

class TatooineModuleConfig {
    val val1: String? = null
    val val2 = 0

    @TypesafeConfig(value = "module.cfg")
    fun provide(): TatooineModuleConfig {
        return TatooineModuleConfig()
    }
}

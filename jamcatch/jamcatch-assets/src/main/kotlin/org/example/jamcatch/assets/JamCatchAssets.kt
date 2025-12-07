package org.example.jamcatch.assets

import org.apache.commons.io.IOUtils
import org.example.javarca.model.Asset
import org.example.javarca.model.AssetSet

class JamCatchAssets : AssetSet {
    private val assets =
        setOf(
            Asset('.', readImage("bg")),
            Asset('p', readImage("catcher")),
            Asset('x', readImage("wall")),
            Asset('X', readImage("solid")),
            Asset('H', readImage("jar_4")),
            Asset('I', readImage("jar_5")),
            Asset('J', readImage("jar_0")),
            Asset('K', readImage("jar_2")),
            Asset('L', readImage("jar_3")),
        )

    private fun readImage(name: String): ByteArray {
        javaClass.getResourceAsStream("res/$name.png").use {
            return IOUtils.toByteArray(it)
        }
    }

    override fun assets(): Set<Asset> {
        return assets
    }
}

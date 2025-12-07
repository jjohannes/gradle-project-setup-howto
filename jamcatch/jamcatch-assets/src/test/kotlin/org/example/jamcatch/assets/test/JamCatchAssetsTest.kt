package org.example.jamcatch.assets.test

import org.example.jamcatch.assets.JamCatchAssets
import org.example.javarca.model.AssetSet
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JamCatchAssetsTest {
    @Test
    fun testAssetSetCreation() {
        val assetSet: AssetSet = JamCatchAssets()

        Assertions.assertNotNull(assetSet)
        val assets = assetSet.assets()
        Assertions.assertNotNull(assets)
        Assertions.assertFalse(assets.isEmpty(), "Asset set should not be empty")
    }
}

package org.example.jamcatch.assets.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;
import org.example.jamcatch.assets.JamCatchAssets;
import org.example.javarca.model.Asset;
import org.example.javarca.model.AssetSet;
import org.junit.jupiter.api.Test;

public class JamCatchAssetsTest {

    @Test
    public void testAssetSetCreation() {
        AssetSet assetSet = new JamCatchAssets();

        assertNotNull(assetSet);
        Set<Asset> assets = assetSet.assets();
        assertNotNull(assets);
        assertFalse(assets.isEmpty(), "Asset set should not be empty");
    }
}

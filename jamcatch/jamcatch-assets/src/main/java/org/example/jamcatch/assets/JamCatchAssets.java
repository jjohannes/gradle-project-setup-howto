package org.example.jamcatch.assets;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.example.javarca.model.Asset;
import org.example.javarca.model.AssetSet;

public class JamCatchAssets implements AssetSet {

    private final Set<Asset> assets;

    public JamCatchAssets() {
        assets = Set.of(
                new Asset('.', readImage("bg")),
                new Asset('p', readImage("catcher")),
                new Asset('x', readImage("wall")),
                new Asset('X', readImage("solid")),
                new Asset('H', readImage("jar_4")),
                new Asset('I', readImage("jar_5")),
                new Asset('J', readImage("jar_0")),
                new Asset('K', readImage("jar_2")),
                new Asset('L', readImage("jar_3")));
    }

    private byte[] readImage(String name) {
        try (InputStream is = getClass().getResourceAsStream("res/" + name + ".png")) {
            return IOUtils.toByteArray(requireNonNull(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Asset> assets() {
        return assets;
    }
}

module org.example.jamcatch.assets {
    requires transitive org.example.javarca.model;
    requires org.apache.commons.io;

    exports org.example.jamcatch.assets;

    provides org.example.javarca.model.AssetSet with
            org.example.jamcatch.assets.JamCatchAssets;
}

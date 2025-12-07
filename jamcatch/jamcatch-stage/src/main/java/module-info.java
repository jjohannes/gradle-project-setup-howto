module org.example.jamcatch.stage {
    requires transitive org.example.javarca.model;
    requires org.slf4j;

    exports org.example.jamcatch.stage;

    provides org.example.javarca.model.Stage with
            org.example.jamcatch.stage.JamCatchStage;
}

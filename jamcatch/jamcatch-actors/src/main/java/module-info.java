module org.example.jamcatch.actors {
    requires transitive org.example.javarca.model;
    requires org.apache.commons.csv;

    exports org.example.jamcatch.actors;

    provides org.example.javarca.model.ActorSet with
            org.example.jamcatch.actors.JamCatchActorSet;
}

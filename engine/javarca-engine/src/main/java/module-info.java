module org.example.javarca.engine {
    requires transitive org.example.javarca.model;
    requires org.slf4j;

    exports org.example.javarca.engine;

    uses org.example.javarca.model.AssetSet;
    uses org.example.javarca.model.Stage;
    uses org.example.javarca.model.ActorSet;
    uses org.example.javarca.engine.Renderer;
}

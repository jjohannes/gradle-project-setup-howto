open module org.example.javarca.engine.test.fixtures {
    exports org.example.javarca.engine.test.fixtures;

    requires org.example.javarca.engine;
    requires org.slf4j;
    requires /*runtime*/ org.example.renderer.lwjgl;
}

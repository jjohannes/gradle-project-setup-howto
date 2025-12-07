module org.example.renderer.lwjgl {
    requires transitive org.example.javarca.engine;
    requires org.lwjgl.glfw;
    requires org.lwjgl.opengl;
    requires org.lwjgl.stb;
    requires org.lwjgl;
    requires org.slf4j;
    requires /*runtime*/ org.lwjgl.glfw.natives;
    requires /*runtime*/ org.lwjgl.natives;
    requires /*runtime*/ org.lwjgl.opengl.natives;
    requires /*runtime*/ org.lwjgl.stb.natives;

    exports org.example.renderer.lwjgl;

    provides org.example.javarca.engine.Renderer with
            org.example.renderer.lwjgl.LWJGLRenderer;
}

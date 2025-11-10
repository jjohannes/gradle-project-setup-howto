package org.example.renderer.lwjgl.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.javarca.engine.Renderer;
import org.example.renderer.lwjgl.LWJGLRenderer;
import org.junit.jupiter.api.Test;

public class LWJGLRendererTest {

    @Test
    public void testRendererCreation() {
        Renderer renderer = new LWJGLRenderer();

        assertNotNull(renderer, "Renderer should not be null");
    }
}

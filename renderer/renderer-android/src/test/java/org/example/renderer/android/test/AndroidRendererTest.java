package org.example.renderer.android.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.javarca.engine.Renderer;
import org.example.renderer.android.AndroidRenderer;
import org.junit.jupiter.api.Test;

public class AndroidRendererTest {

    @Test
    public void testRendererCreation() {
        Renderer renderer = new AndroidRenderer();

        assertNotNull(renderer, "Renderer should not be null");
    }
}

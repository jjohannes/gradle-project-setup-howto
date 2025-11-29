package org.example.renderer.android;

import android.opengl.GLES10;
import android.opengl.GLSurfaceView;

import org.example.javarca.engine.GameLoop;
import org.example.javarca.engine.GameState;
import org.example.javarca.engine.Renderer;
import org.example.javarca.engine.Spot;

import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class AndroidRenderer implements GLSurfaceView.Renderer, Renderer {

    private final GameLoop gameLoop = new GameLoop();
    private final GameState gameState = new GameState();
    private final Map<Character, Integer> images = new HashMap<>();

    @Override
    public void run() {
        gameLoop.start(gameState);
    }

    public void stop() {
        gameLoop.stop();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        init();
    }

    private void init() {
        GLES10.glClearColor(0.8f, 0.8f, 0.8f, 1.0f);
        GLES10.glEnable(GLES10.GL_TEXTURE_2D);
        GLES10.glEnable(GLES10.GL_BLEND);
        GLES10.glBlendFunc(GLES10.GL_SRC_ALPHA, GLES10.GL_ONE_MINUS_SRC_ALPHA);

        gameState.getImages().forEach((symbol, content) ->
                images.put(symbol, loadTexture(content)));
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES10.glClearColor(1f, 1f, 1f, 0.0f);
        GLES10.glClear(GLES10.GL_COLOR_BUFFER_BIT | GLES10.GL_DEPTH_BUFFER_BIT);
        gameState.getSpots().forEach(this::drawSpot);
    }

    private void drawSpot(Spot s) {
        drawSprite(ensureTexture(s), s.getPixelPositionX(16), s.getPixelPositionY(16));
    }

    private Integer ensureTexture(Spot spot) {
        return images.get(spot.getSymbol());
    }

    private void drawSprite(Integer img, float x, float y) {
        if (img == null || x == Float.MIN_VALUE) {
            return;
        }
        renderTexture(img, x, y, 16, 16);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES10.glViewport(0, 0, width, height);
    }

    private int loadTexture(byte[] data) {
        // TODO implement
        // int[] textures = new int[1];
        // GLES10.glGenTextures(1, textures, 0);
        // int textureId = textures[0];
        // GLES10.glBindTexture(GLES10.GL_TEXTURE_2D, textureId);
        // Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        // GLUtils.texImage2D(GLES10.GL_TEXTURE_2D, 0, bitmap, 0);
        // bitmap.recycle();
        // GLES10.glTexParameterx(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MIN_FILTER, GLES10.GL_LINEAR);
        // GLES10.glTexParameterx(GLES10.GL_TEXTURE_2D, GLES10.GL_TEXTURE_MAG_FILTER, GLES10.GL_LINEAR);
        // return textureId;
        return 0;
    }

    public void renderTexture(int textureId, float x, float y, float width, float height) {
        // TODO implement
        // GLES10.glActiveTexture(GLES10.GL_TEXTURE0);
        // GLES10.glBindTexture(GLES10.GL_TEXTURE_2D, textureId);
        // float[] identityMvp = {
        //         1f, 0f, 0f, 0f,
        //         0f, 1f, 0f, 0f,
        //         0f, 0f, 1f, 0f,
        //         0f, 0f, 0f, 1f
        // };
        // GLES2TexturedQuad quad = new GLES2TexturedQuad();
        // quad.draw(textureId, x, y, width, height, identityMvp);
    }
}

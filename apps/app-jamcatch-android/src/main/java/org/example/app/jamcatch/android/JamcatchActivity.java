package org.example.app.jamcatch.android;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import org.example.renderer.android.AndroidRenderer;

public class JamcatchActivity extends Activity {

    private AndroidRenderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderer = new AndroidRenderer();
        GLSurfaceView gameView = new GLSurfaceView(this);
        gameView.setEGLContextClientVersion(2); // Use OpenGL ES 2.0
        gameView.setRenderer(renderer);
        setContentView(gameView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        renderer.stop();
    }
}

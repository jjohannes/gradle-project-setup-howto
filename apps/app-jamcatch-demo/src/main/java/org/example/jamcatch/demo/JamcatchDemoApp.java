package org.example.jamcatch.demo;

import org.example.javarca.engine.Renderer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JamcatchDemoApp {

    public static void main(String[] args) {
        SpringApplication.run(JamcatchDemoApp.class, args);
        Renderer.launch();
    }
}

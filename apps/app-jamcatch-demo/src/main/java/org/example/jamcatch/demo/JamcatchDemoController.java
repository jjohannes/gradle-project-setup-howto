package org.example.jamcatch.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JamcatchDemoController {

    @GetMapping("/")
    public String index() {
        return """
            <html>
            <head>
                <meta http-equiv="refresh" content="1" />
            </head>
            <body>
                <img src="screen.png" style="width: 768px"/>
            </body>
            </html>""";
    }

    @GetMapping(value = "/screen.png", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] screen() throws IOException {
        return Files.readAllBytes(Path.of("build/demo/screen.png"));
    }
}

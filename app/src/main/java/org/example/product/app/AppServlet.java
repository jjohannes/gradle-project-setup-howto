package org.example.product.app;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppServlet {

    @GetMapping("/")
    public String index() throws IOException {
        new MainModule().run();
        String version = new BufferedReader(
                        new InputStreamReader(requireNonNull(AppServlet.class.getResourceAsStream("/version.txt"))))
                .readLine();
        return "<html><body>" + "App is running... !!!!" + "<br/>Version " + version + "</body></html>";
    }
}

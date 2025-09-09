package org.example.product.kashyyyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.example.product.bespin.BespinModule;
import org.example.product.kamino.KaminoModule;
import org.example.product.naboo.NabooModule;
import org.example.product.tatooine.TatooineModule;
import android.widget.Button;

public class KashyyykModule {
    public Button button;

    public KashyyykModule() {
        try (InputStream hello = KashyyykModule.class.getResourceAsStream("hello.txt")) {
            String message = new BufferedReader(new InputStreamReader(hello)).readLine();
            System.out.println(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NabooModule flyTo(TatooineModule from) {
        new KaminoModule();
        new BespinModule();
        int a = calculateSomethingImportant();
        return new NabooModule(a);
    }

    int calculateSomethingImportant() {
        return 4 + 1;
    }
}

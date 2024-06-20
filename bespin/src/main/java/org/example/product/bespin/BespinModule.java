package org.example.product.bespin;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.velocity.io.VelocityWriter;
import org.example.product.corellia.CorelliaModule;
import org.example.product.coruscant.CoruscantModule;

public class BespinModule {

    public CorelliaModule doThings() throws IOException {
        new CoruscantModule();
        new VelocityWriter(new FileWriter(System.getProperty("java.io.tmpdir") + "/dummy.out"));
        return new CorelliaModule();
    }
}

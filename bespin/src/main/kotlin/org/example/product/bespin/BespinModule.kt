package org.example.product.bespin

import java.io.FileWriter
import org.apache.velocity.io.VelocityWriter
import org.example.product.corellia.CorelliaModule
import org.example.product.coruscant.CoruscantModule

class BespinModule {

    fun doThings(): CorelliaModule {
        CoruscantModule()
        VelocityWriter(FileWriter(System.getProperty("java.io.tmpdir") + "/dummy.out"))
        return CorelliaModule()
    }
}

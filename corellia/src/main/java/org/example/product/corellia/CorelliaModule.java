package org.example.product.corellia;

import java.io.IOException;
import org.apache.commons.io.input.NullInputStream;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ss.usermodel.Workbook;

public class CorelliaModule {

    public CorelliaModule() {}

    public Workbook parse() throws IOException {
        new NullInputStream(); // transitive dependency of poi, used directly
        POIXMLDocument.openPackage("...");
        return null;
    }
}

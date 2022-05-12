package org.example.product.corellia;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.commons.io.input.NullInputStream;

import java.io.IOException;

public class CorelliaModule {

    public Workbook parse() throws IOException {
        new NullInputStream(); // transitive dependency of poi, used directly
        POIXMLDocument.openPackage("...");
        return null;
    }
}

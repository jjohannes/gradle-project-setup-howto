package org.example.product.corellia

import org.apache.commons.io.input.NullInputStream
import org.apache.poi.ooxml.POIXMLDocument
import org.apache.poi.ss.usermodel.Workbook

class CorelliaModule {

    fun parse(): Workbook? {
        NullInputStream() // transitive dependency of poi, used directly
        POIXMLDocument.openPackage("...")
        return null
    }
}

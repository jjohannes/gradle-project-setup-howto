package org.example.product.kamino

import org.example.product.coruscant.CoruscantModuleData

/** I am published and therefore I have Javadoc! */
class KaminoModule {

    /** Data... */
    var data: CoruscantModuleData? = null

    /**
     * Info!
     *
     * @return all the important Classes
     */
    fun info(): Array<Class<*>> {
        return arrayOf()
    }
}

package org.example.product.tatooine

import org.apache.zookeeper.ZooKeeper

class TatooineModule {
    fun twinSunState(): String {
        return ZooKeeper.States.CONNECTED.toString()
    }
}

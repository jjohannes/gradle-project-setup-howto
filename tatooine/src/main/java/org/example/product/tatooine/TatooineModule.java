package org.example.product.tatooine;

import org.apache.zookeeper.ZooKeeper;

public class TatooineModule {

    public String twinSunState() {
        return ZooKeeper.States.CONNECTED.toString();
    }
}

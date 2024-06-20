package org.example.product.naboo;

import java.util.Collections;
import org.apache.jute.Utils;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;

public class NabooModule {

    public NabooModule() {
        dive();
    }

    public NabooModule(int importantNumber) {}

    private int dive() {
        SolrParams params = new MapSolrParams(Collections.singletonMap("a1", ""));
        return Utils.compareBytes(params.get("a1").getBytes(), 0, 0, new byte[0], 0, 0);
    }
}

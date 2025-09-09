package org.example.product.app.mock.api;

import com.google.common.collect.ImmutableList;
import org.example.product.app.MainModule;

public class MockServer {

    public MockServer() {}

    public ImmutableList<String> get() {
        new MainModule();
        return ImmutableList.of("a", "b", "x", "y");
    }
}

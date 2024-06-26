package org.example.product.app.endtoend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.ImmutableList;
import org.example.product.app.mock.api.MockServer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("slow")
public class SystemTest {

    @Test
    void testAll() {
        assertEquals(0, sendStuff(new MockServer().get()));
    }

    private int sendStuff(ImmutableList<String> things) {
        return 0;
    }
}

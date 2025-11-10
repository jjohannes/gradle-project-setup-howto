package org.example.jamcatch.actors.end2end;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.javarca.engine.test.fixtures.RendererFixture;
import org.example.javarca.engine.test.fixtures.Screenshot;
import org.junit.jupiter.api.Test;

public class JamCatchActorsEnd2EndTest {

    @Test
    public void test() {
        Screenshot result = RendererFixture.launchAndWaitForFinish();

        assertTrue(result.file().exists(), "Screenshot should exist");
    }
}

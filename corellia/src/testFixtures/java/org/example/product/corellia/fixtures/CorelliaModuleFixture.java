package org.example.product.corellia.fixtures;

import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteProcessor;
import org.example.product.corellia.CorelliaModule;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

public class CorelliaModuleFixture implements ByteProcessor<CorelliaModuleFixture> {
    public List<CorelliaModule> threeModules() {
        new CorelliaModule();
        return ImmutableList.of(new CorelliaModule(), new CorelliaModule(), new CorelliaModule());
    }

    @Override
    public boolean processBytes(@Nonnull byte[] buf, int off, int len) throws IOException {
        return false;
    }

    @Override
    public @Nullable CorelliaModuleFixture getResult() {
        return null;
    }
}

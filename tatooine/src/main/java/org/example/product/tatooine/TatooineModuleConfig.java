package org.example.product.tatooine;

import com.github.racc.tscg.TypesafeConfig;

public class TatooineModuleConfig {

    private String val1;
    private int val2;

    public String getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }

    @TypesafeConfig(value = "module.cfg")
    public TatooineModuleConfig provide() {
        return new TatooineModuleConfig();
    }
}

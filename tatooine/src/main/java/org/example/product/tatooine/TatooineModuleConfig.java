package org.example.product.tatooine;

public class TatooineModuleConfig {

    private final String val1;
    private final int val2;

    public TatooineModuleConfig(String val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public String getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }

    public TatooineModuleConfig provide() {
        return new TatooineModuleConfig("-", 64);
    }
}

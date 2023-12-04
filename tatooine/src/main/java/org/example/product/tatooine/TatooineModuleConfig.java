package org.example.product.tatooine;

public class TatooineModuleConfig {

    private String val1;
    private int val2;

    public String getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }

    public TatooineModuleConfig provide() {
        return new TatooineModuleConfig();
    }

}

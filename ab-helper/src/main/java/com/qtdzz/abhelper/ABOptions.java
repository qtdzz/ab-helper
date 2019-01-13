package com.qtdzz.abhelper;

public class ABOptions {
    private final ABType type;
    private String[] ab;

    public ABOptions(ABType type, String... ab) {
        this.type = type;
        this.ab = ab;
    }

    public ABType getType() {
        return type;
    }

    public String[] getAb() {
        return ab;
    }

    public enum ABType {
        CLASS, THEME
    }
}

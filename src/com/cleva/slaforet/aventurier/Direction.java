package com.cleva.slaforet.aventurier;

public enum Directions {
    N("North", 0, -1),
    E("East", 1, 0),
    S("South", 0, 1),
    W("West", -1, 0);

    String label;
    int xOffset, yOffset;

    Directions(String label, int xOffset, int yOffset) {
        this.label = label;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public String getLabel() {
        return label;
    }
}

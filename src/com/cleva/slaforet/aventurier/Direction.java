package com.cleva.slaforet.aventurier;

public enum Direction {
    N("Nord", 0, -1),
    E("Est", 1, 0),
    S("Sud", 0, 1),
    O("Ouest", -1, 0);

    final String label;
    final int xOffset;
    final int yOffset;

    Direction(String label, int xOffset, int yOffset) {
        this.label = label;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public static boolean isValidDirection(String direction) {
        try {
            Direction.valueOf(direction);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

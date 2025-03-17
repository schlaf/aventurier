package com.cleva.slaforet.aventurier;

import java.util.Objects;

/**
 * position d'un aventurier sur la carte
 */
public class Position {
    // axe x, démarre à 0, sens Est-Ouest (0 = point le plus à l'ouest)
    private int x;
    // axe y, démarre à 0, sens Nord-Sud. (0 = point le plus au Nord)
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position initialPosition, Direction direction) {
        this.x = initialPosition.getX() + direction.xOffset;
        this.y = initialPosition.getY() + direction.yOffset;
    }

    public Position(Position initialPosition) {
        this.x = initialPosition.getX();
        this.y = initialPosition.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return "Position[x=" + x + ", y=" + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

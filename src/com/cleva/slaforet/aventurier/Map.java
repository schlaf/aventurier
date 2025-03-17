package com.cleva.slaforet.aventurier;


import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * répresente la carte et les possibilités de déplacement
 */
public class Map {

    static Logger LOGGER = Logger.getLogger("Map");
    private static final char EMPTY_SPACE = ' ';

    /**
     * taille de la carte sur l'axe X (Est-ouest)
     */
    private int xSize;
    /**
     * taille de la carte sur l'axe Y (Nord-sud)
     */
    private int ySize;
    /**
     * tableau interne représentatif. Attention : le 1er tableau est l'axe Y, le 2ème l'axe X !!!
     */
    private char[][] innerMap;

    static NumberFormat nf = NumberFormat.getIntegerInstance();

    public Map(String textSource) {
        innerMap = MapParser.parse(textSource);
        xSize = innerMap[0].length;
        ySize = innerMap.length;
        nf.setMinimumIntegerDigits(2);
        LOGGER.setLevel(Level.FINER);
    }

    /**
     * Déplace une position avec une direction, en respect des possibilités (bord de carte + zones de bois)
     * @param currentPosition la position initiale
     * @param direction la direction souhaitée
     * @return la position atteinte
     */
    public Position moveTo(Position currentPosition, Direction direction) {
        boolean canMove = true;

        // check the borders of the map
        switch (direction) {
            case E -> canMove = (currentPosition.getX() < xSize - 1 ) ; // end of map on the right direction
            case O -> canMove = (currentPosition.getX() > 0) ; // end of map on the left direction
            case N -> canMove = (currentPosition.getY() > 0 ) ; // end of map on the top direction
            case S -> canMove = (currentPosition.getY() < ySize - 1 ) ; // end of map on the bottom direction
        }
        LOGGER.finer("check map borders : " + (canMove?"can move to ":"cannot move to " ) + direction.label);
        if (canMove) {
            Position wannabePosition = new Position(currentPosition, direction);
            if (isPositionLegale(wannabePosition)) {
                LOGGER.finer("new position is OK");
                return wannabePosition;
            } else {
                LOGGER.finer("new position is in the woods! won't move");
                return currentPosition;
            }
        } else {
            return currentPosition;
        }
    }

    /**
     * indique si la position est légitime au vu de la carte (=dans les coordonnées possibles + la case n'est pas du bois)
     * @param position
     * @return
     */
    private boolean isPositionLegale(Position position) {
        assert position != null;
        assert position.getX() >= 0 && position.getX() < xSize - 1;
        assert position.getY() >= 0 && position.getY() < ySize - 1;
        return innerMap[position.getY()][position.getX()] == EMPTY_SPACE;
    }

    /**
     * renvoie une chaine multi-ligne qui affiche la carte + la position
     * @param position
     * @return
     */
    public String displayMap(Position position) {
        StringBuffer sb = new StringBuffer();
        sb.append("    ");
        for (int i = 0; i < xSize; i++) {
            sb.append(i%10);
        }
        for (int i = 0; i < ySize; i++) {
            sb.append("\n");
            sb.append(nf.format(i)).append("--");
            for (int j = 0; j < xSize; j++) {
                if (i == position.getY() && j == position.getX()) {
                    sb.append('X');
                } else {
                    sb.append(innerMap[i][j]);
                }
            }
        }
        return sb.toString();
    }
}

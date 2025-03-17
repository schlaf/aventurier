package com.cleva.slaforet.aventurier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    Map map;
    @BeforeEach
    void setUp() throws IOException {
        String input = Files.readString(Path.of("resources/correct_map.txt"));
        map = new Map(input);
    }

    @Test
    void testMoveToNorthImpossibleBorders() {
        Position position = new Position(0, 0);
        Position newPosition = map.moveTo(position, Direction.N); // impossible
        assertNotNull(newPosition);
        assertEquals(newPosition, position);
    }

    @Test
    void testMoveToEastImpossibleBorders() {
        Position position = new Position(19, 0);
        Position newPosition = map.moveTo(position, Direction.E); // impossible
        assertNotNull(newPosition);
        assertEquals(newPosition, position);
    }

    @Test
    void testMoveToSouthImpossibleBorders() {
        Position position = new Position(0, 19);
        Position newPosition = map.moveTo(position, Direction.S); // impossible
        assertNotNull(newPosition);
        assertEquals(newPosition, position);
    }

    @Test
    void testMoveToWestImpossibleBorders() {
        Position position = new Position(0, 0);
        Position newPosition = map.moveTo(position, Direction.O); // impossible
        assertNotNull(newPosition);
        assertEquals(newPosition, position);
    }


    @Test
    void testMoveToEastPossible() {
        Position position = new Position(4, 0);
        Position newPosition = map.moveTo(position, Direction.E); // possible
        assertNotNull(newPosition);
        Position position2 = new Position(position, Direction.E);
        assertEquals(newPosition, position2);
    }

    @Test
    void testMoveToSouthPossible() {
        Position position = new Position(4, 0);
        Position newPosition = map.moveTo(position, Direction.S); // possible
        assertNotNull(newPosition);
        Position position2 = new Position(position, Direction.S);
        assertEquals(newPosition, position2);
    }

    @Test
    void testCantMoveDueToWoods() {
        Position position = new Position(3, 0);
        Position newPosition = map.moveTo(position, Direction.O); // impossible
        assertNotNull(newPosition);
        assertEquals(newPosition, position);
    }

    @Test
    void testDisplayMap1() {
        Position position = new Position(0, 0);
        String display = map.displayMap(position);
        assertNotNull(display);
        String expected =
"""
    01234567890123456789
00--X##    ######    ###
01--###      ##      ###
02--##     ##  ##     ##
03--#      ##  ##      #
04--##                ##
05--#####          #####
06--###### ##  ##  #####
07-- #     ######     #\s
08--      ########     \s
09--     ############  \s
10--     ############  \s
11--      ########     #
12-- #      ######    ##
13--###### ##  ## ######
14--#####          #####
15--##                ##
16--#   ## #    # ##   #
17--##   ##      ##   ##
18--###    #    #    ###
19--###    ######    ###""";
        assertEquals(expected, display);

    }

    @Test
    void testDisplayMap2() {
        Position position = new Position(5, 7);
        String display = map.displayMap(position);
        assertNotNull(display);
        String expected =
"""
    01234567890123456789
00--###    ######    ###
01--###      ##      ###
02--##     ##  ##     ##
03--#      ##  ##      #
04--##                ##
05--#####          #####
06--###### ##  ##  #####
07-- #   X ######     #\s
08--      ########     \s
09--     ############  \s
10--     ############  \s
11--      ########     #
12-- #      ######    ##
13--###### ##  ## ######
14--#####          #####
15--##                ##
16--#   ## #    # ##   #
17--##   ##      ##   ##
18--###    #    #    ###
19--###    ######    ###""";
        assertEquals(expected, display);

    }

}
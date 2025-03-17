package com.cleva.slaforet.aventurier;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MapParserTest {


    @Test
    public void testParseOK() throws IOException {
        String input = Files.readString(Path.of("resources/correct_map.txt"));

        char[][] map = MapParser.parse(input);
        assertNotNull(map);
        assertEquals(20, map.length);
        assertEquals(20, map[0].length);
        assertEquals('#', map[0][0]);
        assertEquals(' ', map[4][4]);
    }

    @Test
    public void testParseFail1() throws IOException {
        String input = Files.readString(Path.of("resources/invalid_map1.txt"));
        assertThrowsExactly(AssertionError.class, () -> MapParser.parse(input));
    }

    @Test
    public void testParseFail2() throws IOException {
        String input = Files.readString(Path.of("resources/invalid_map2.txt"));
        assertThrowsExactly(AssertionError.class, () -> MapParser.parse(input));
    }

    @Test
    public void testParseFail3() throws IOException {
        String input = Files.readString(Path.of("resources/invalid_map3.txt"));
        assertThrowsExactly(AssertionError.class, () -> MapParser.parse(input));
    }
}
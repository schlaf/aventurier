package com.cleva.slaforet.aventurier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapParserTest {


    @Test
    public void testParse() {
        MapParser mapParser = new MapParser();
        Map map = mapParser.parse("mqldkjfqmlkdj");
        assertNotNull(map);
    }
}
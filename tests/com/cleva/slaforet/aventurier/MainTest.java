package com.cleva.slaforet.aventurier;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main1() {
        Main.main(new String[]{"test1.txt"});
        Position result = Main.getFinalPosition();
        Position expected = new Position(9, 2);
        assertEquals(expected, result);
    }

    @Test
    void main2() {
        Main.main(new String[]{"test2.txt"});
        Position result = Main.getFinalPosition();
        Position expected = new Position(5, 7); // not like the PDF!!! typo in the exercice?
        assertEquals(expected, result);
    }

    @Test
    void mainFail() {
        assertThrowsExactly(RuntimeException.class, () -> Main.main(new String[]{"testPifPafPouf.txt"}));
    }

    @Test
    void mainFail2() {
        Main.main(new String[]{});
        assertNull(Main.getFinalPosition());
    }
}
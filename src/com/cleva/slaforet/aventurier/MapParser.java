package com.cleva.slaforet.aventurier;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * classe de parsing de la carte au format texte
 * la carte doit être au format
 * <pre>
 * ###    ######    ###
 * ###      ##      ###
 * ##     ##  ##     ##
 * </pre>
 * ' ' = case libre
 * '#' = bois impénétrable
 * autre = invalide!
 */
public class MapParser {
    public static char[][] parse(String textSource) {
        ArrayList<char[]> lines = new ArrayList<>();
        Scanner lineScanner = new Scanner(new StringReader(textSource));
        lineScanner.useDelimiter("\n");

        int lineSize = -1;
        int expectedLineSize = 0;
        while (lineScanner.hasNext()) {
            String line = lineScanner.next();
            char[] lineContent = line.toCharArray();
            for (char c : lineContent) {
                assert c == ' ' || c == '#';
            }
            if (lineSize == -1) {
                expectedLineSize = lineContent.length; // first pass, initialize the line size expected
            }
            lineSize = lineContent.length;
            assert lineSize > 0;
            assert lineSize == expectedLineSize; // ensure each line has the same x size
            lines.add(lineContent);
        }

        return lines.toArray(new char[lines.size()][]);
    }
}

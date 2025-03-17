package com.cleva.slaforet.aventurier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    /** position initialisée avec le ficher d'instruction */
    private Position initialPosition;
    /** la chaine des directions à suivre */
    private String instructions;
    /**
     * la carte (initialisée en durà
     */
    private Map map;

    private static Position finalPosition;

    private static Logger LOGGER = null;

    static {
        InputStream stream = Main.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            LOGGER = Logger.getLogger("aventurer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LOGGER.info("Start program");
        if (args.length != 1) {
            LOGGER.severe("1 argument attendu : nom du fichier d'initialisation");
            return;
        }

        Main program = new Main();
        program.init(args);
        finalPosition = program.run();
    }

    private void init(String[] args) {
        try {
            String input = Files.readString(Path.of("resources/" + args[0]));
            initFromInput(input);
            initMap();

        } catch (Exception e) {
            LOGGER.severe("Erreur d'initialisation : " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private Position run() {
        Position p = new Position(initialPosition);
        LOGGER.info(p.toString());
        char[] instructionsArray = instructions.toCharArray();
        for(char c : instructionsArray) {
            LOGGER.info("== try to move to " + c);
            p = map.moveTo(p, Direction.valueOf(String.valueOf(c)));
            LOGGER.info(p.toString());
            LOGGER.finer("\n" + map.displayMap(p));
        }
        LOGGER.info("================================");
        LOGGER.info("Position finale : ");
        LOGGER.info(p.toString());
        LOGGER.info("================================");
        return p;
    }

    private void initMap() throws Exception, AssertionError {
        LOGGER.info("initialisation de la carte");
        String content = Files.readString(Path.of("resources/correct_map.txt")); // voir à rendre ça paramétrable?
        this.map = new Map(content);
    }

    private void initFromInput(String input) throws Exception {
        LOGGER.info("initialisation des instructions de l'aventurier");
        ArrayList<char[]> lines = new ArrayList<>();
        Scanner lineScanner = new Scanner(new StringReader(input));
        lineScanner.useDelimiter("\n");

        String firstLine = lineScanner.next();
        try {
            Scanner positionScanner = new Scanner(firstLine);
            positionScanner.useDelimiter(",");
            String x = positionScanner.next();
            String y = positionScanner.next();
            this.initialPosition = new Position(Integer.parseInt(x), Integer.parseInt(y));
        } catch (Exception e) {
            LOGGER.severe("invalid instruction first line : must be line x,y");
            throw new RuntimeException("invalid instruction first line : must be line x,y");
        }


        String secondLine = lineScanner.next();
        boolean invalidInputs = secondLine.chars()
                .mapToObj(Character::toString)
                .anyMatch(value -> (! Direction.isValidDirection(new String(value))));

        if (invalidInputs) {
            LOGGER.severe("invalid instruction line : all characters must be NSEO only");
            throw new RuntimeException("invalid instruction line : all characters must be NSEO only");
        }

        this.instructions = secondLine;
        LOGGER.info("running with init position = " +  initialPosition.toString());
        LOGGER.info("running with instructions = " +  instructions);
    }

    public static Position getFinalPosition() {
        return finalPosition;
    }
}
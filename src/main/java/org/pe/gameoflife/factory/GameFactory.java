package org.pe.gameoflife.factory;

import org.pe.gameoflife.logic.ConsoleGameImpl;
import org.pe.gameoflife.logic.Game;
import org.pe.gameoflife.model.CellGeneration;

import java.util.Scanner;

public class GameFactory {

    private GameFactory() {
    }

    public static Game newConsoleGameFromUserInput() {
        Scanner sc = new Scanner(System.in);

        // Get width and height.
        final int width = sc.nextInt();
        final int height = sc.nextInt();

        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Width or height less than 1 is not allowed.");
        }

        sc.nextLine();

        // Create a new cell generation.
        CellGeneration initialCellGeneration = new CellGeneration(width, height);

        // Read x and y for cells to be alive
        // in the initial generation, until user
        // inputs an empty line.
        String input;
        while (!(input = sc.nextLine()).equals("")) {

            // Get x and y from input.
            String[] args = input.split(" ");

            // Expect 2 arguments.
            if (args.length != 2) {
                throw new IllegalArgumentException(String.format("Expected 2 arguments but got %d", args.length));
            }

            final int x = Integer.parseInt(args[0]);
            final int y = Integer.parseInt(args[1]);

            initialCellGeneration.setAlive(x, y, true);

        }

        sc.close();

        return new ConsoleGameImpl(initialCellGeneration);
    }
}

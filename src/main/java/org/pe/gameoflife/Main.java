package org.pe.gameoflife;


import org.pe.gameoflife.factory.GameFactory;
import org.pe.gameoflife.logic.Game;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        // Prepare game from user input.
        Game game = GameFactory.newConsoleGameFromUserInput();

        while (true) {

            // Render the game field.
            game.render();

            // Update game state.
            game.update();

            // Sleep for 1 second.
            Thread.sleep(1000);

        }
    }

}

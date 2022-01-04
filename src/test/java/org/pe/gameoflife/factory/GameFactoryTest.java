package org.pe.gameoflife.factory;

import org.junit.jupiter.api.Test;
import org.pe.gameoflife.logic.Game;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameFactoryTest {

    @Test
    void test_newConsoleGameFromUserInput_oneInitialLivingCell() {
        // Given
        final String userInput = "5 5\n0 0\n\n";
        final InputStream stdIn = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // When
        final Game game = GameFactory.newConsoleGameFromUserInput();

        // Then
        assertNotNull(game);

        System.setIn(stdIn);
    }

    @Test
    void test_newConsoleGameFromUserInput_noInitialLivingCell() {
        // Given
        final String userInput = "5 5\n\n";
        final InputStream stdIn = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        // When
        final Game game = GameFactory.newConsoleGameFromUserInput();
        // Then
        assertNotNull(game);

        System.setIn(stdIn);
    }

    @Test
    void test_newConsoleGameFromUserInput_illegalWidthOrHeight() {
        // Given
        final String userInput = "0 1\n\n";
        final InputStream stdIn = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // Then
        assertThrows(IllegalArgumentException.class, GameFactory::newConsoleGameFromUserInput);

        System.setIn(stdIn);

    }

    @Test
    void test_newConsoleGameFromUserInput_wrongNumberOfArgs() {
        // Given
        final String userInput = "1 1\n1\n\n";
        final InputStream stdIn = System.in;
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // Then
        assertThrows(IllegalArgumentException.class, GameFactory::newConsoleGameFromUserInput);

        System.setIn(stdIn);
    }
}

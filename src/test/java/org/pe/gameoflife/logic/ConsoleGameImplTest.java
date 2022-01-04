package org.pe.gameoflife.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.pe.gameoflife.model.CellGeneration;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class ConsoleGameImplTest {

    private String cellFieldOutput;

    @BeforeEach
    void setup() {
        cellFieldOutput = null;
    }

    @Test
    void test_renderGameField() {

        // Given
        final PrintStream stdOut = System.out;
        final PrintStream printStreamMock = mock(PrintStream.class);

        doAnswer((Answer<Void>) invocation -> {

            // Get method parameter.
            String output = invocation.getArgument(0);

            // Remove new line and whitespace.
            cellFieldOutput = output.replaceAll("[\n|\s]", "");

            return null;
        }).when(printStreamMock).println(anyString());
        System.setOut(printStreamMock);

        CellGeneration initialCellGeneration = new CellGeneration(2, 1);
        initialCellGeneration.setAlive(0, 0, true);

        Game game = new ConsoleGameImpl(initialCellGeneration);

        // When
        game.update();
        game.render();

        // Then
        assertEquals("..", cellFieldOutput);

        System.setOut(stdOut);

    }
}

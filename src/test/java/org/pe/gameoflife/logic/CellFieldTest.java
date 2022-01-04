package org.pe.gameoflife.logic;

import org.junit.jupiter.api.Test;
import org.pe.gameoflife.model.Cell;
import org.pe.gameoflife.model.CellGeneration;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CellFieldTest {

    @Test
    void test_livingCellWithThreeNeighbours() {
        // Given
        CellGeneration initialCellGeneration = new CellGeneration(5, 5);
        initialCellGeneration.setAlive(2, 2, true);
        initialCellGeneration.setAlive(3, 2, true);
        initialCellGeneration.setAlive(2, 3, true);
        initialCellGeneration.setAlive(3, 3, true);

        CellField cellField = new CellField(initialCellGeneration);

        // When
        cellField.nextCellGeneration();

        // Then
        assertCells(cellField.getCells(), Arrays.asList(12, 13, 17, 18));
    }

    @Test
    void test_livingCellWithOneNeighbour() {
        // Given
        CellGeneration initialCellGeneration = new CellGeneration(5, 5);
        initialCellGeneration.setAlive(2, 2, true);
        initialCellGeneration.setAlive(3, 2, true);

        CellField cellField = new CellField(initialCellGeneration);

        // When
        cellField.nextCellGeneration();

        // Then
        assertCells(cellField.getCells(), List.of());
    }

    @Test
    void test_livingCellWIthFourNeighbours() {
        // Given
        CellGeneration initialCellGeneration = new CellGeneration(5, 5);
        initialCellGeneration.setAlive(2, 2, true);
        initialCellGeneration.setAlive(4, 2, true);
        initialCellGeneration.setAlive(2, 4, true);
        initialCellGeneration.setAlive(4, 4, true);
        initialCellGeneration.setAlive(3, 3, true);

        CellField cellField = new CellField(initialCellGeneration);

        // When
        cellField.nextCellGeneration();

        // Then
        assertCells(cellField.getCells(), Arrays.asList(13, 17, 19, 23));
    }

    @Test
    void test_deadCellWithThreeNeighbours() {
        // Given
        CellGeneration initialCellGeneration = new CellGeneration(5, 5);
        initialCellGeneration.setAlive(2, 2, true);
        initialCellGeneration.setAlive(2, 4, true);
        initialCellGeneration.setAlive(4, 3, true);

        CellField cellField = new CellField(initialCellGeneration);

        // When
        cellField.nextCellGeneration();

        // Then
        assertCells(cellField.getCells(), List.of(18));
    }

    @Test
    void test_deadCellWithTwoNeighbours() {
        // Given
        CellGeneration initialCellGeneration = new CellGeneration(5, 5);
        initialCellGeneration.setAlive(2, 2, true);
        initialCellGeneration.setAlive(4, 2, true);

        CellField cellField = new CellField(initialCellGeneration);

        // When
        cellField.nextCellGeneration();

        // Then
        assertCells(cellField.getCells(), List.of());
    }

    private void assertCells(List<Cell> cells, List<Integer> indicesOfExpectedLivingCells) {
        for (int i = 0; i < cells.size(); i++) {

            final Cell current = cells.get(i);
            assertNotNull(current);

            // Assert that the current cell i alive if it's expected
            // to, else assert that the cell is dead.
            if (indicesOfExpectedLivingCells.contains(i)) {
                assertTrue(current.isAlive());
            } else {
                assertFalse(current.isAlive());
            }

        }
    }
}

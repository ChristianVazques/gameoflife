package org.pe.gameoflife.logic;

import org.pe.gameoflife.model.Cell;
import org.pe.gameoflife.model.CellGeneration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class CellField {

    private CellGeneration cellGeneration;

    CellField(final CellGeneration cellGeneration) {
        this.cellGeneration = cellGeneration;
    }

    List<Cell> getCells() {
        return cellGeneration.getCells();
    }

    void nextCellGeneration() {
        // Create a new cell generation for the next generation of cells.
        final int width = cellGeneration.getWidth();
        final int height = cellGeneration.getHeight();
        final CellGeneration nextGeneration = new CellGeneration(width, height);

        for (int i = 0; i < getCells().size(); i++) {

            // Get x and y of index.
            final int x = i % height;
            final int y = i / width;
            final int neighbours;
            final Cell current = cellGeneration.getCell(x, y);

            // Count surrounding neighbours.
            neighbours = Stream.of(
                            cellGeneration.getCell(x, y - 1),
                            cellGeneration.getCell(x, y + 1),
                            cellGeneration.getCell(x - 1, y),
                            cellGeneration.getCell(x - 1, y - 1),
                            cellGeneration.getCell(x - 1, y + 1),
                            cellGeneration.getCell(x + 1, y),
                            cellGeneration.getCell(x + 1, y - 1),
                            cellGeneration.getCell(x + 1, y + 1))
                    .filter(Objects::nonNull)
                    .mapToInt(cell -> cell.isAlive() ? 1 : 0)
                    .sum();

            // Decide if cell shall be dead or alive in the next generation.
            final boolean isAlive = current != null
                    && ((!current.isAlive() && neighbours == 3)
                    || (current.isAlive() && neighbours < 4 && neighbours > 1));

            // Update cell in next generation of cells.
            nextGeneration.setAlive(x, y, isAlive);
        }

        this.cellGeneration = nextGeneration;
    }
}

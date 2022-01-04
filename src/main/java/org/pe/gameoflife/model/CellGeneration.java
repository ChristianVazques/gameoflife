package org.pe.gameoflife.model;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class CellGeneration {
    private final int width;
    private final int height;
    private final List<Cell> cells;

    public CellGeneration(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.cells = createNewCells();
    }

    public void setAlive(final int x, final int y, final boolean alive) {
        final Cell cell = getCell(x, y);
        if (cell != null) {
            cell.setAlive(alive);
        }
    }

    public Cell getCell(final int x, final int y) {
        // Convert x any y to index.
        final int index = y * height + x;

        // Coordinate out of bounds.
        if (x < 0 || x > width - 1
                || y < 0 || y > height - 1
                || index < 0 || index >= cells.size()) {
            return null;
        }

        return cells.get(index);
    }

    private List<Cell> createNewCells() {
        return IntStream.range(0, width * height)
                .mapToObj(i -> new Cell(false))
                .collect(Collectors.toList());
    }
}

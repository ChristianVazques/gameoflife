package org.pe.gameoflife.logic;

import org.pe.gameoflife.model.Cell;
import org.pe.gameoflife.model.CellGeneration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleGameImpl implements Game {

    private final CellField cellField;
    private final int width;

    public ConsoleGameImpl(final CellGeneration initialCellGeneration) {
        this.width = initialCellGeneration.getWidth();
        this.cellField = new CellField(initialCellGeneration);
    }

    @Override
    public void update() {
        cellField.nextCellGeneration();
    }

    @Override
    public void render() {
        final List<Cell> cells = cellField.getCells();

        // Prepare output string.
        final String output = IntStream.range(0, cells.size())
                .mapToObj(i -> (i % width == 0 ? "\n" : "") + cells.get(i).getSymbol())
                .collect(Collectors.joining(" "));

        System.out.println(output);
    }
}

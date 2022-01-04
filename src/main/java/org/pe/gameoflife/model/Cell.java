package org.pe.gameoflife.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cell {
    private boolean alive;
    public String getSymbol() {
        return alive ? "o" : ".";
    }
}

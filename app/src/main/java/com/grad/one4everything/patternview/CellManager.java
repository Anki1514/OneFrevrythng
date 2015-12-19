package com.grad.one4everything.patternview;

import java.util.Arrays;

public class CellManager implements Manager<Cell> {
    private boolean[][] patternDrawLookup;
    private Cell[][] cells;
    private int rows;
    private int columns;
    private int size;

    public CellManager(final int rows, final int columns) {
        CellUtils.checkRange(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.size = rows * columns;
        patternDrawLookup = new boolean[rows][columns];
        cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j);
            }
            Arrays.fill(patternDrawLookup[i], false);
        }
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public synchronized Cell get(final int row, final int column) {
        return cells[row][column];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        cells = new Cell[][]{};
    }

    @Override
    public void draw(Cell cell, final boolean drawn) {
        final int row = cell.getRow();
        final int column = cell.getColumn();
        patternDrawLookup[row][column] = drawn;
    }

    @Override
    public void draw(int row, int column, final boolean drawn) {
        patternDrawLookup[row][column] = drawn;
    }

    @Override
    public void clearDrawing() {
        for (int i = 0; i < rows; i++) {
            Arrays.fill(patternDrawLookup[i], false);
        }
    }

    @Override
    public boolean isDrawn(int row, int column) {
        return patternDrawLookup[row][column];
    }

    @Override
    public boolean isDrawn(Cell cell) {
        final int row = cell.getRow();
        final int column = cell.getColumn();
        return patternDrawLookup[row][column];
    }
}

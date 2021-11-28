package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues{
    int width;
    int height;
    int[][] cluesRow;
    int[][] cluesColumn;

    public CluesImpl(int[][] cluesRow, int[][] cluesColumn) {
        if(cluesRow == null || cluesColumn == null) {
            throw new IllegalArgumentException();
        }
        this.cluesRow = cluesRow;
        this.cluesColumn = cluesColumn;
    }


    @Override
    public int getWidth() {
        return cluesColumn.length;
    }

    @Override
    public int getHeight() {
        return cluesRow.length;
    }

    @Override
    public int[] getRowClues(int index) {
        return cluesRow[index];
    }

    @Override
    public int[] getColClues(int index) {
        return cluesColumn[index];
    }

    @Override
    public int getRowCluesLength() {
        return cluesRow[0].length;
    }

    @Override
    public int getColCluesLength() {
        return cluesColumn[0].length;
    }
}

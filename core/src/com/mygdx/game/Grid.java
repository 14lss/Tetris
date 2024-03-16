package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Grid {
    final private int numRows ,numCols, cellSize;
    public int[][] grid = new int[20][10];
    private Color[] colors;

    public Grid() {
        numRows = 20;
        numCols = 10;
        cellSize = 30;
        colors = Colors.getColors();
        initialize();
    }

    public void initialize() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                grid[row][col] = 0;
            }
        }
    }

    public void print() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    public boolean isCellOutside(int row, int col) {
        return row < 0 || row >= numRows || col < 0 || col >= numCols;
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == 0;
    }

    public int clearFullRows() {
        int completed = 0;
        for (int row = numRows - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                clearRow(row);
                completed++;
            }
            else if (completed > 0) {
                moveRowDown(row, completed);
            }
        }
        return completed;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int cellValue = grid[row][col];
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(colors[cellValue]);
                shapeRenderer.rect(col * cellSize + 11, row * cellSize + 11, cellSize - 1, cellSize - 1);
                shapeRenderer.end();
            }
        }
    }

    private boolean isRowFull(int row) {
        for (int col = 0; col < numCols; col++) {
            if (grid[row][col] == 0) {
                return false;
            }
        }
        return true;
    }

    private void clearRow(int row) {
        for (int col = 0; col < numCols; col++) {
            grid[row][col] = 0;
        }
    }

    private void moveRowDown(int row, int numRows) {
        for (int col = 0; col < numCols; col++) {
            grid[row + numRows][col] = grid[row][col];
            grid[row][col] = 0;
        }
    }
}

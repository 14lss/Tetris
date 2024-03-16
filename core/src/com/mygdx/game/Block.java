package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    public int id;
    public Position[][] cells;
    private final int cellSize;
    private int rotationState, rowOffset, colOffset;
    private final Color[] colors;

    public Block() {
        cellSize = 30;
        rotationState = 0;
        rowOffset = 0;
        colOffset = 0;
        colors = Colors.getColors();
    }

    public void draw(ShapeRenderer shapeRenderer, int offsetX, int offSetY) {
        Position[] tiles = getPositions();
        for (Position item : tiles) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(colors[id]);
            shapeRenderer.rect(item.col * cellSize + offsetX, item.row * cellSize + offSetY, cellSize - 1, cellSize - 1);
            shapeRenderer.end();
        }
    }

    public void move(int rows, int cols) {
        rowOffset += rows;
        colOffset += cols;
    }

    public void rotate() {
        rotationState++;
        if (rotationState == cells.length) {
            rotationState = 0;
        }
    }

    public void undoRotation() {
        rotationState--;
        if (rotationState == -1) {
            rotationState = cells.length - 1;
        }
    }

    public Position[] getPositions() {
        Position[] tiles = cells[rotationState];
        Position[] movedTiles = new Position[4];
        for (int i = 0; i < tiles.length; i++) {
            Position newPos = new Position(tiles[i].row + rowOffset, tiles[i].col + colOffset);
            movedTiles[i] = newPos;
        }
        return movedTiles;
    }
}

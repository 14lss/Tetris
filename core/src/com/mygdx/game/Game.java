package com.mygdx.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.blocks.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {
    public Grid grid;
    public boolean gameOver;
    public int score;
    public Music music;
    public Sound clearSound, rotateSound;
    private ArrayList<Block> blocks;
    private Block currentBlock, nextBlock;

    public Game() {
        grid = new Grid();
        gameOver = false;
        score = 0;
        blocks = getBlocks();
        currentBlock = getRandomBlock();
        nextBlock = getRandomBlock();
        music = Gdx.audio.newMusic(Gdx.files.internal("Sound/Sounds_music.mp3"));
        music.setLooping(true);
        music.play();
        clearSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Sounds_clear.mp3"));
        rotateSound = Gdx.audio.newSound(Gdx.files.internal("Sound/Sounds_rotate.mp3"));
    }

    private Block getRandomBlock() {
        if (blocks.isEmpty()) blocks = getBlocks();
        int randomIndex = new Random().nextInt(blocks.size());
        Block block = blocks.get(randomIndex);
        blocks.remove(randomIndex);
        return block;
    }

    private ArrayList<Block> getBlocks() {
        return new ArrayList<>(Arrays.asList(new IBlock(), new JBlock(), new LBlock(), new OBlock(), new SBlock(), new TBlock(), new ZBlock()));
    }

    public void draw(ShapeRenderer shapeRenderer) {
        grid.draw(shapeRenderer);
        currentBlock.draw(shapeRenderer, 11, 11);
        switch (nextBlock.id) {
            case 3:
                nextBlock.draw(shapeRenderer, 255, 290);
                break;
            case 4:
                nextBlock.draw(shapeRenderer, 255, 280);
                break;
            default:
                nextBlock.draw(shapeRenderer, 270, 270);
                break;
        }

    }

    public void handleInput() {
        if (gameOver && Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            gameOver = false;
            reset();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            moveLeft();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            moveRight();
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            moveDown();
            updateScore(0, 1);
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            rotateBlock();
        }
    }

    private void reset() {
        grid.initialize();
        blocks = getBlocks();
        currentBlock = getRandomBlock();
        nextBlock = getRandomBlock();
        score = 0;
    }

    private void rotateBlock() {
        if (!gameOver) {
            currentBlock.rotate();
            if (isBlockOutside() || !blockFits()) currentBlock.undoRotation();
            else rotateSound.play();
        }
    }

    private void moveLeft() {
        if (!gameOver) {
            currentBlock.move(0, -1);
            if (isBlockOutside() || !blockFits()) currentBlock.move(0, 1);
        }
    }

    private void moveRight() {
        if (!gameOver) {
            currentBlock.move(0, 1);
            if (isBlockOutside() || !blockFits()) currentBlock.move(0, -1);
        }
    }

    public void moveDown() {
        if (!gameOver) {
            currentBlock.move(1, 0);
            if (isBlockOutside() || !blockFits()) {
                currentBlock.move(-1, 0);
                lockBlock();
            }
        }
    }

    private boolean isBlockOutside() {
        Position[] tiles = currentBlock.getPositions();
        for (Position item : tiles) {
            if (grid.isCellOutside(item.row, item.col)) return true;
        }
        return false;
    }

    private void lockBlock() {
        Position[] tiles = currentBlock.getPositions();
        for (Position item : tiles) {
            grid.grid[item.row][item.col] = currentBlock.id;
        }
        currentBlock = nextBlock;
        if (!blockFits()) gameOver = true;
        nextBlock = getRandomBlock();
        int rowsCleared = grid.clearFullRows();
        if (rowsCleared > 0) {
            clearSound.play();
            updateScore(rowsCleared, 0);
        }
    }

    private boolean blockFits() {
        Position[] tiles = currentBlock.getPositions();
        for (Position item : tiles) {
            if (!grid.isCellEmpty(item.row, item.col)) return false;
        }
        return true;
    }

    private void updateScore(int linesCleared, int moveDownPoints) {
        switch (linesCleared) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
            default:
                break;
        }
        score += moveDownPoints;
    }
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

public class Tetris extends ApplicationAdapter {
	BitmapFont font;
	GlyphLayout scoreText;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	Batch batch;
	Game game;

	@Override
	public void create () {
		font = new BitmapFont(Gdx.files.internal("Font/VT323.fnt"), true);
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		game = new Game();
		scoreText = new GlyphLayout(font, "0");
		Timer.instance().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				game.moveDown();
			}
		}, 0.5f, 0.5f);
	}


    @Override
	public void render () {
		ScreenUtils.clear(Colors.darkblue);
		game.handleInput();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Colors.lightblue);
		shapeRenderer.rect(320, 55, 170, 60);
		shapeRenderer.rect(320, 215, 170, 180);
		shapeRenderer.end();
		game.draw(shapeRenderer);
		batch.begin();
		font.getData().setScale(0.25f);
		font.setColor(Color.WHITE);
		font.draw(batch, "SCORE", 365, 15);
		font.draw(batch, "NEXT", 370, 175);
		scoreText.setText(font, Integer.toString(game.score));
		font.draw(batch, scoreText, 320 + (170 - scoreText.width) / 2, 65);
		if (game.gameOver) font.draw(batch, "GAME OVER", 335, 450);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		game.music.dispose();
		game.clearSound.dispose();
		game.rotateSound.dispose();
	}
}

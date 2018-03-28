package de.android.ayrathairullin.montyhall;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import de.android.ayrathairullin.montyhall.managers.GameManager;
import de.android.ayrathairullin.montyhall.managers.InputManager;

public class Monty extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private float w, h;
	private Viewport viewport;

	@Override
	public void create() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		camera.setToOrtho(false);
		batch = new SpriteBatch();
		GameManager.initialize(w, h);
		viewport = new FitViewport(w, h, camera);
	}

	@Override
	public void dispose() {
		batch.dispose();
		GameManager.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		InputManager.handleInput(camera);

		batch.begin();
		GameManager.renderGame(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}

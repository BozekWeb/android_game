package pl.bozek.dungeon;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import pl.bozek.dungeon.screen.main.MainLoadingScreen;

public class DungeonGame extends Game {

	// == attributes ==
	private PooledEngine engine;
	private AssetManager assetManager;
	private SpriteBatch batch;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);
		batch = new SpriteBatch();
		engine = new PooledEngine();
		setScreen(new MainLoadingScreen(this));
		System.out.println("CREATE");
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		batch.dispose();
	}


	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public PooledEngine getEngine() {
		return engine;
	}




}
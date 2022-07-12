package pl.bozek.dungeon.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.bozek.dungeon.DungeonGame;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.database.DataBase;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) GameConfig.WIDTH;
		config.height = (int) GameConfig.HEIGHT;
		new LwjglApplication(new DungeonGame(), config);
		DataBase.prefixHost = "http://localhost/";
	}
}

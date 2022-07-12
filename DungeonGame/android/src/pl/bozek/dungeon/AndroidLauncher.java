package pl.bozek.dungeon;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import pl.bozek.dungeon.DungeonGame;
import pl.bozek.dungeon.database.DataBase;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new DungeonGame(), config);
		DataBase.prefixHost = "http://10.0.2.2/";
	}
}

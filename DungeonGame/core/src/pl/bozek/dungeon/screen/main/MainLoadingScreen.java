package pl.bozek.dungeon.screen.main;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pl.bozek.dungeon.DungeonGame;
import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.common.LoginPlayer;
import pl.bozek.dungeon.screen.BasicScreen;
import pl.bozek.dungeon.screen.game.DungeonScreen;
import pl.bozek.dungeon.screen.menu.MainMenuScreen;
import pl.bozek.dungeon.util.GdxUtils;

public class MainLoadingScreen extends BasicScreen {

    // == constants ==
    private final AssetManager assetManager;

    private float waitTime = 0.75f;
    private boolean changeScreen;

    public MainLoadingScreen(DungeonGame game) {
        super(game);
        assetManager = game.getAssetManager();
    }


    @Override
    public void show() {
        super.show();
        assetManager.load(AssetDescriptors.MAP_TILESET);
        assetManager.load(AssetDescriptors.ENTITY_TILESET);
        assetManager.load(AssetDescriptors.BASIC_FONT);
        assetManager.load(AssetDescriptors.HUD_TILE);
        assetManager.load(AssetDescriptors.UI_SKIN );
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        update(delta);

        GdxUtils.clearScreen();

        getViewport().apply();
        getRenderer().setProjectionMatrix(getCamera().combined);
        getRenderer().begin(ShapeRenderer.ShapeType.Filled);

        getRenderer().end();

        if(changeScreen){
            getGame().setScreen(new MainMenuScreen(getGame()));
        }
    }



    private void update(float delta){
        //waitMillis(400);

        //progress is between 0 and 1
        assetManager.getProgress();

        //update return true when all assets are loaded
        if(assetManager.update()){
            waitTime -= delta;
            if(waitTime <= 0){
                changeScreen = true;
            }
        }
    }


}




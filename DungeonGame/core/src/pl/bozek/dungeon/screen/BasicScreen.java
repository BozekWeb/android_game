package pl.bozek.dungeon.screen;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.bozek.dungeon.DungeonGame;
import pl.bozek.dungeon.util.GdxUtils;


/**
 *  Basic screen model. Other Screen extend it.
 * */

public class BasicScreen implements Screen {

    /** Basic debug storage */
    private static final Logger log = new Logger(BasicScreen.class.getName(), Logger.DEBUG);

    /** Attributes used in all screens */
    private final DungeonGame game;
    private final PooledEngine engine;
    private final AssetManager assetManager;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    public BasicScreen(DungeonGame game){
        this.game = game;
        this.engine = game.getEngine();
        this.batch = game.getBatch();
        this.assetManager = game.getAssetManager();
        this.renderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport( 21, 40, camera);
    }


    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        GdxUtils.clearScreen();
        getEngine().update(delta);





    }

    @Override
    public void resize(int width, int height) {


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        log.debug("hide() - hide basic screen");
        dispose();
    }

    @Override
    public void dispose() {

    }


    public DungeonGame getGame() {
        return game;
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public ShapeRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(ShapeRenderer renderer) {
        this.renderer = renderer;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
}

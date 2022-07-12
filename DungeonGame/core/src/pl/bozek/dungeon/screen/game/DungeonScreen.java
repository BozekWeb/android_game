package pl.bozek.dungeon.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.bozek.dungeon.DungeonGame;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.common.GameHudFactory;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.config.GameHudConfig;
import pl.bozek.dungeon.map.RoomInfo;
import pl.bozek.dungeon.screen.main.MainLoadingScreen;
import pl.bozek.dungeon.system.enemy.EnemyDeadSystem;
import pl.bozek.dungeon.system.enemy.EnemyFindPathSystem;
import pl.bozek.dungeon.system.enemy.EnemyMovementSystem;
import pl.bozek.dungeon.system.enemy.EnemySpeedManipulationSystem;
import pl.bozek.dungeon.system.graphics.PlayerMovementTextureChangeSystem;
import pl.bozek.dungeon.system.player.PlayerFreeMoveSpeedManipulationSystem;
import pl.bozek.dungeon.system.player.PlayerMovementSystem;
import pl.bozek.dungeon.system.player.PlayerNextDungeonLevelSystem;
import pl.bozek.dungeon.system.player.PlayerSingleAttackSystem;
import pl.bozek.dungeon.system.player.PlayerSpeedManipulationSystem;
import pl.bozek.dungeon.system.player.PlayerStrongAttackSystem;
import pl.bozek.dungeon.system.turnBase.basic.EnemyTurnStartSystem;
import pl.bozek.dungeon.system.turnBase.basic.PlayerTurnStartSystem;
import pl.bozek.dungeon.system.turnBase.freeMovement.BreakFreeMovementSystem;
import pl.bozek.dungeon.screen.BasicScreen;

import pl.bozek.dungeon.system.hud.HudChangeExpBarSystem;
import pl.bozek.dungeon.system.hud.HudChangeHitPointSystem;
import pl.bozek.dungeon.system.debug.DebugRenderRectangleSystem;
import pl.bozek.dungeon.system.hud.HudRenderSystem;
import pl.bozek.dungeon.system.inputHandle.GameInputHandler;
import pl.bozek.dungeon.system.inputHandle.HudInputHandler;
import pl.bozek.dungeon.system.player.PlayerLevelUpSystem;
import pl.bozek.dungeon.system.graphics.RenderSystem;
import pl.bozek.dungeon.system.player.PlayerDungeonSpawnLocationSystem;
import pl.bozek.dungeon.system.map.random.GenerateMapTilesSystem;
import pl.bozek.dungeon.system.enemy.EnemyStartSpawnSystem;
import pl.bozek.dungeon.system.turnBase.basic.CheckTurnSystem;
import pl.bozek.dungeon.system.turnBase.freeMovement.StartFreeMovementSystem;
import pl.bozek.dungeon.util.GdxUtils;


public class DungeonScreen extends BasicScreen {

    private final Viewport hudViewport;
    private final OrthographicCamera hudCamera;
    private final GameHudFactory hudFactory;
    public DungeonScreen(DungeonGame game) {
        super(game);
        resetCurrentData();

        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport(GameHudConfig.HUD_WIDTH, GameHudConfig.HUD_HEIGHT, hudCamera);
        hudFactory = new GameHudFactory(getEngine(), getAssetManager());

        new GenerateMapTilesSystem(getEngine(), getAssetManager());

        new PlayerDungeonSpawnLocationSystem(getEngine(), getAssetManager(), getCamera());

        new EnemyStartSpawnSystem( getEngine(), getAssetManager() );

    }

    @Override
    public void show() {
        super.show();


        /* TURN BASE SYSTEMS */
        getEngine().addSystem( new CheckTurnSystem() );
        getEngine().addSystem( new StartFreeMovementSystem() );
        getEngine().addSystem( new BreakFreeMovementSystem() );
        getEngine().addSystem( new PlayerTurnStartSystem() );
        getEngine().addSystem( new EnemyTurnStartSystem() );



        /* PLAYER SYSTEMS */
        getEngine().addSystem( new PlayerFreeMoveSpeedManipulationSystem() );
        getEngine().addSystem( new PlayerSpeedManipulationSystem(getAssetManager()) );
        getEngine().addSystem( new PlayerMovementSystem( getCamera() ) );
        getEngine().addSystem( new PlayerLevelUpSystem() );
        getEngine().addSystem( new PlayerSingleAttackSystem() );
        getEngine().addSystem( new PlayerStrongAttackSystem() );


        /* ENEMY SYSTEMS */
        getEngine().addSystem( new EnemyFindPathSystem() );
        getEngine().addSystem( new EnemySpeedManipulationSystem() );
        getEngine().addSystem( new EnemyMovementSystem() );



        /* RPG SYSTEMS */
        getEngine().addSystem( new EnemyDeadSystem() );


        /* GRAPHICS SYSTEMS */
        getEngine().addSystem(new RenderSystem(getViewport(), getBatch()));
        getEngine().addSystem( new PlayerMovementTextureChangeSystem( getAssetManager() ) );

        /* DEBUG MODE */







        getEngine().addSystem( new PlayerNextDungeonLevelSystem() );

        if(GameConfig.DEBUG){
       //     getEngine().addSystem(new DebugRenderRectangleSystem(getViewport(), getRenderer()));
        }


        /* HUD SYSTEMS */
        createHUD();
        getEngine().addSystem( new HudChangeHitPointSystem( getAssetManager()  ) );
        getEngine().addSystem( new HudChangeExpBarSystem() );
        getEngine().addSystem(new HudRenderSystem(hudViewport, getBatch(), getAssetManager()));

        /* INPUT HANDLE */
        InputProcessor hudInput = new HudInputHandler(hudCamera, getEngine());
        InputProcessor gameInput = new GameInputHandler(getCamera(), getEngine());
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(hudInput);
        multiplexer.addProcessor(gameInput);
        Gdx.input.setInputProcessor( multiplexer );
    }

    private void resetCurrentData(){

        CurrentData.ENEMY_TURN = false;
        CurrentData.ENEMY_TURN_START = false;
        CurrentData.ENEMY_ACTION_ARRAY = new Array<>(  );
        CurrentData.CLICKED_ENEMY = null;
        CurrentData.ENEMY_CAN_MOVE = false;
        CurrentData.ENEMY_FIND_PATH = false;
        CurrentData.PLAYER_ATTACK_TYPE = 1;
        CurrentData.FREE_MOVEMENT = true;
        CurrentData.BREAK_FREE_MOVEMENT = false;
        CurrentData.PLAYER_TURN_START = false;
        CurrentData.PLAYER_CAN_CLICK = true;





    }



    @Override
    public void render(float delta) {
        GdxUtils.clearScreen(new Color(37/255f, 19/255f, 26/255f,1/255f));
        GdxUtils.clearScreen();

        getEngine().update(delta);


        if(CurrentData.PLAYER_DEAD){
            CurrentData.PLAYER_DEAD = false;
            getEngine().removeAllEntities();
            getGame().setScreen( new DungeonScreen(getGame()) );

        }



    }



    @Override
    public void resize(int width, int height) {
        hudViewport.update(width, height, true);
        getViewport().update(width, height);


    }



    private void createHUD(){


        hudFactory.addMainFrame();
        hudFactory.addExpBarFrame();

        hudFactory.addLvlFrame();
        hudFactory.addAPFrame();
        hudFactory.addStrongAttackButton();
        hudFactory.addExpBar();
        hudFactory.addHpBar();






    }


}

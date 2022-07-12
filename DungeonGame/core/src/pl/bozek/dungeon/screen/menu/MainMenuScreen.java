package pl.bozek.dungeon.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.bozek.dungeon.DungeonGame;
import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.assets.RegionNames;

import pl.bozek.dungeon.common.GameHudFactory;
import pl.bozek.dungeon.common.LoginPlayer;

import pl.bozek.dungeon.common.PlayerData;
import pl.bozek.dungeon.config.GameHudConfig;
import pl.bozek.dungeon.screen.BasicScreen;
import pl.bozek.dungeon.screen.game.DungeonScreen;
import pl.bozek.dungeon.screen.menu.loginScreens.LoginScreen;


public class MainMenuScreen extends BasicScreen {


    private final Viewport hudViewport;
    private final OrthographicCamera hudCamera;
    private final GameHudFactory hudFactory;

    Stage stage;
    Skin skin = new Skin( Gdx.files.internal( "hud/uiskin.json" ) );




    public MainMenuScreen(DungeonGame game){
        super(game);
        hudCamera = new OrthographicCamera();
        hudViewport = new FitViewport( GameHudConfig.HUD_WIDTH, GameHudConfig.HUD_HEIGHT, hudCamera);
        hudFactory = new GameHudFactory(getEngine(), getAssetManager());
    }



    @Override
    public void show() {
        super.show();
        this.stage = new Stage( hudViewport, getBatch() );
        stage.addActor( createUI() );
        Gdx.input.setInputProcessor( stage );
    }


    private Actor createUI(){

        TextureAtlas hudAtlas = getAssetManager().get( AssetDescriptors.HUD_TILE );
        Table mainTable = new Table(  );
        TextureRegion backgroundRegion = hudAtlas.findRegion(RegionNames.MENU_BG2);
        mainTable.setBackground(new TextureRegionDrawable(backgroundRegion));
        Table buttonTable = new Table();
        buttonTable.defaults().pad(10);

        Label separate = new Label( "", skin );


        Label game = new Label( "Dungeon Game", skin );
        game.setFontScale( 3f );


        //Quest Player Loaded
        if(PlayerData.isPlayerLoaded && PlayerData.isQuest){
            // Continue as Quest
            TextButton continueButton = new TextButton("      CONTINUE       ", skin);
            continueButton.getLabel().setFontScale( 2.5f );
            continueButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Continue as Quest");
                    getGame().setScreen( new DungeonScreen( getGame() ) );
                }
            });

            // Save Quest Progress
            TextButton continueButton11 = new TextButton("  SAVE PROGRESS  ", skin);
            continueButton11.getLabel().setFontScale( 2.5f );
            continueButton11.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Continue as Quest");
                    getGame().setScreen( new DungeonScreen( getGame() ) );
                }
            });


            buttonTable.add( separate ).row();
            buttonTable.add( continueButton ).row();
            buttonTable.add( continueButton11 ).row();
            buttonTable.add( separate ).row();

            //DataBase Player Loaded
        } else if(PlayerData.isPlayerLoaded && !PlayerData.isQuest){
            // Continue as DataBase Player
            TextButton continueButton = new TextButton("      CONTINUE        ", skin);
            continueButton.getLabel().setFontScale( 2.5f );
            continueButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Continue as DataBase Player");
                    getGame().setScreen( new DungeonScreen( getGame() ) );
                }
            });

            // DataBase Player settings
            TextButton characterButton = new TextButton("      CHARACTER      ", skin);
            characterButton.getLabel().setFontScale( 2.5f );
            characterButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Character");
                    PlayerData.GeneratePlayer();
                    getGame().setScreen( new DungeonScreen( getGame() ) );
                }
            });

            // DataBase Player settings
            TextButton logoutButton = new TextButton("         LOG OUT        ", skin);
            logoutButton.getLabel().setFontScale( 2.5f );
            logoutButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    PlayerData.isQuest = false;
                    PlayerData.playerID = -1;
                    PlayerData.isPlayerLoaded = false;
                    getGame().setScreen( new MainMenuScreen( getGame() ) );
                }
            });

            buttonTable.add( separate ).row();
            buttonTable.add( continueButton ).row();
            buttonTable.add( characterButton ).row();
            buttonTable.add( logoutButton ).row();
            buttonTable.add( separate ).row();

        } else{

            // PLAY
            TextButton playButton = new TextButton("            PLAY            ", skin);
            playButton.getLabel().setFontScale( 2.5f );
            playButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Play");

                    getGame().setScreen( new LoginScreen( getGame() ) );

                }
            });

            // PLAY AS QUEST
            TextButton questButton = new TextButton("   PLAY AS GUEST   ", skin);
            questButton.getLabel().setFontScale( 2.5f );
            questButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("Play as Guest");
                    PlayerData.GeneratePlayer();
                    getGame().setScreen( new DungeonScreen( getGame() ) );
                }
            });


            buttonTable.add( separate ).row();
            buttonTable.add( playButton ).row();
            buttonTable.add( questButton ).row();
            buttonTable.add( separate ).row();


        }



        // Setting
        TextButton settingButton = new TextButton("        SETTINGS        ", skin);
        settingButton.getLabel().setFontScale( 2.5f );
        settingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("settings");
            }
        });

        // Credit/Info
        TextButton infoButton = new TextButton("      GAME INFO      ", skin);
        infoButton.getLabel().setFontScale( 2.5f );
        infoButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("settings");
            }
        });

        // Credit/Info
        TextButton exitButton = new TextButton("            EXIT             ", skin);
        exitButton.getLabel().setFontScale( 2.5f );
        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });



        // setup table


        buttonTable.add(settingButton).row();
        buttonTable.add(infoButton).row();
        buttonTable.add(exitButton).row();


        buttonTable.center();
        mainTable.add( game ).row();
        mainTable.add(buttonTable);
        mainTable.center();
        mainTable.setFillParent(true);
        mainTable.pack();

        return mainTable;
    }

    private void play(){
        getGame().setScreen( new DungeonScreen( getGame() ) );
    }


    @Override
    public void render(float delta) {
        super.render( delta );
        stage.act();
        stage.draw();


    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }
}



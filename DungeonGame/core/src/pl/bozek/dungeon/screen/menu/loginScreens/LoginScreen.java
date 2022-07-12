package pl.bozek.dungeon.screen.menu.loginScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import pl.bozek.dungeon.config.GameHudConfig;
import pl.bozek.dungeon.screen.BasicScreen;
import pl.bozek.dungeon.screen.game.DungeonScreen;
import pl.bozek.dungeon.screen.menu.MainMenuScreen;
import pl.bozek.dungeon.system.databse.WaitLoginRespondSystem;

public class LoginScreen extends BasicScreen {


    private final Viewport hudViewport;
    private final OrthographicCamera hudCamera;
    private final GameHudFactory hudFactory;

    Stage stage;
    Skin skin = new Skin( Gdx.files.internal( "hud/uiskin.json" ) );




    public LoginScreen(DungeonGame game){
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

        getEngine().addSystem( new WaitLoginRespondSystem(getGame()) );

    }


    private Actor createUI(){

      //  skin.getFont( "default-font" ).getData().setScale( 2 );

        TextureAtlas hudAtlas = getAssetManager().get( AssetDescriptors.HUD_TILE );
        Table mainTable = new Table(  );
        TextureRegion backgroundRegion = hudAtlas.findRegion( RegionNames.MENU_BG2);
        mainTable.setBackground(new TextureRegionDrawable(backgroundRegion));
        Table buttonTable = new Table();
        buttonTable.defaults().pad(10);

        Label separate = new Label( "", skin );


        Label game = new Label( "Dungeon Game", skin );
        game.setFontScale( 3f );

        buttonTable.add( game ).row();

        Label error = new Label( "", skin );
        error.setFontScale( 2f );
        error.setColor( new Color( Color.RED ) );
        buttonTable.add( error ).row();

        Label loginLabel = new Label( "Login:", skin );
        loginLabel.setFontScale( 2 );
        skin.getFont( "default-font" ).getData().setScale( 2.5f );
        TextField login = new TextField( "", skin );
        login.setWidth( 1000f );

        Label passwordLabel = new Label( "Password:", skin );
        passwordLabel.setFontScale( 2 );
        TextField password = new TextField( "", skin );

        password.setPasswordMode( true );
        password.setPasswordCharacter( '*' );

        TextButton loginButton = new TextButton( "  LOG IN  ", skin );
        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Log in to DataBase");
                WaitLoginRespondSystem.LOGIN_START = true;
                WaitLoginRespondSystem.username = login.getText();
                WaitLoginRespondSystem.password = password.getText();
                Gdx.input.setOnscreenKeyboardVisible(false);



            }
        });


        TextButton registerButton = new TextButton( "  REGISTER  ", skin );

        TextButton backButton = new TextButton( " BACK " , skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Back to MainMenu");
                getGame().setScreen( new MainMenuScreen( getGame() ) );
            }
        });


        buttonTable.add( loginLabel );
        buttonTable.add( login ).center().row();
        buttonTable.add( passwordLabel );
        buttonTable.add( password ).row();
        buttonTable.add( separate ).row();
        buttonTable.add( registerButton );
        buttonTable.add( loginButton ).row();
        buttonTable.add( separate ).row();

        buttonTable.add( backButton ).center();



        buttonTable.center();
        mainTable.add( game ).row();
        mainTable.add(buttonTable);
        mainTable.add( separate );
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



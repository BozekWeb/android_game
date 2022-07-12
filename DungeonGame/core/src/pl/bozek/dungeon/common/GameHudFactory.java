package pl.bozek.dungeon.common;

import com.badlogic.ashley.core.Entity;

import com.badlogic.ashley.core.PooledEngine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.assets.RegionNames;

import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.FreeMoveHudComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.element.RectangleBoundComponent;
import pl.bozek.dungeon.component.element.TextureComponent;
import pl.bozek.dungeon.component.element.ValueComponent;
import pl.bozek.dungeon.component.tag.ActionPointComponent;
import pl.bozek.dungeon.component.tag.ClickableComponent;
import pl.bozek.dungeon.component.tag.ExperienceComponent;
import pl.bozek.dungeon.component.tag.HPComponent;
import pl.bozek.dungeon.component.tag.HUDComponent;
import pl.bozek.dungeon.config.GameHudConfig;

public class GameHudFactory {

    private PooledEngine engine;
    private TextureAtlas hudAtlas;

    public GameHudFactory(PooledEngine engine, AssetManager assetManager){
        this.engine = engine;
        hudAtlas = assetManager.get(AssetDescriptors.HUD_TILE);
    }

    public void addMainFrame(){
        //Add corners
        addHudNoClickElement( 0, 0,
                GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_LEFT_DOWN_CORNER);

        addHudNoClickElement( 0, GameHudConfig.HUD_HEIGHT - GameHudConfig.HUD_NORMAL_SIZE,
                GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_LEFT_UP_CORNER);

        addHudNoClickElement( GameHudConfig.HUD_WIDTH - GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_HEIGHT - GameHudConfig.HUD_NORMAL_SIZE,
                GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_RIGHT_UP_CORNER);

        addHudNoClickElement( GameHudConfig.HUD_WIDTH - GameHudConfig.HUD_NORMAL_SIZE, 0,
                GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_RIGHT_DOWN_CORNER);


        //Add walls
        addHudNoClickElement( 0, GameHudConfig.HUD_NORMAL_SIZE,
                GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_HEIGHT - 2 * GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_LEFT_WALL);


        addHudNoClickElement( GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_HEIGHT - GameHudConfig.HUD_NORMAL_SIZE,
                GameHudConfig.HUD_WIDTH - 2 * GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_UP_WALL);

        addHudNoClickElement( GameHudConfig.HUD_WIDTH - GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_NORMAL_SIZE,
                GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_HEIGHT - 2 * GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_RIGHT_WALL);

        addHudNoClickElement( GameHudConfig.HUD_NORMAL_SIZE, 0,
                GameHudConfig.HUD_WIDTH - 2 * GameHudConfig.HUD_NORMAL_SIZE, GameHudConfig.HUD_NORMAL_SIZE,
                RegionNames.HUD_MAIN_FRAME_DOWN_WALL);
    }

    public void addHpBar(){
        HUDComponent tag = engine.createComponent(HUDComponent.class);
        HPComponent hpTag = engine.createComponent( HPComponent.class );

        FreeMoveHudComponent freeMoveHud = engine.createComponent( FreeMoveHudComponent.class );
        freeMoveHud.freeMove = true;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = GameHudConfig.HUD_HP_BAR_X;
        position.y = GameHudConfig.HUD_HP_BAR_Y;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameHudConfig.HUD_HP_BAR_WIDTH;
        dimension.height = GameHudConfig.HUD_HP_BAR_HEIGHT;

        RectangleBoundComponent rectangleBoundComponent = engine.createComponent(RectangleBoundComponent.class);
        rectangleBoundComponent.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_10 );

        Entity hpBar = new Entity();
        hpBar.add( hpTag );
        hpBar.add(position);
        hpBar.add(dimension);
        hpBar.add(texture);
        hpBar.add( freeMoveHud );
        hpBar.add(tag);
        hpBar.add(rectangleBoundComponent);
        engine.addEntity(hpBar);

    }

    public void addExpBarFrame(){
        addHudNoClickElement( GameHudConfig.HUD_EXP_BAR_GRAY_PLATE_X, GameHudConfig.HUD_EXP_BAR_GRAY_PLATE_Y,
                GameHudConfig.HUD_EXP_BAR_GRAY_PLATE_WIDTH, GameHudConfig.HUD_EXP_BAR_GRAY_PLATE_HEIGHT,
                RegionNames.HUD_GRAY_PLATE);

        addHudNoClickElement( GameHudConfig.HUD_EXP_BAR_LIGHT_PLATE_X, GameHudConfig.HUD_EXP_BAR_LIGHT_PLATE_Y,
                GameHudConfig.HUD_EXP_BAR_LIGHT_PLATE_WIDTH, GameHudConfig.HUD_EXP_BAR_LIGHT_PLATE_HEIGHT,
                RegionNames.HUD_LIGHT_PLATE);

        addHudNoClickElement( GameHudConfig.HUD_EXP_BAR_DARK_PLATE_X, GameHudConfig.HUD_EXP_BAR_DARK_PLATE_Y,
                GameHudConfig.HUD_EXP_BAR_DARK_PLATE_WIDTH, GameHudConfig.HUD_EXP_BAR_DARK_PLATE_HEIGHT,
                RegionNames.HUD_DARK_PLATE);
    }

    public void addLvlFrame(){
        addHudNoClickElement( GameHudConfig.HUD_LVL_FRAME_GRAY_PLATE_X, GameHudConfig.HUD_LVL_FRAME_GRAY_PLATE_Y,
                GameHudConfig.HUD_LVL_FRAME_GRAY_PLATE_WIDTH, GameHudConfig.HUD_LVL_FRAME_GRAY_PLATE_HEIGHT,
                RegionNames.HUD_GRAY_PLATE);

        addHudNoClickElement( GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_X, GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_Y,
                GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_WIDTH, GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_HEIGHT,
                RegionNames.HUD_LIGHT_PLATE);

        addHudNoClickElement( GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_X_1, GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_Y_1,
                GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_WIDTH_1, GameHudConfig.HUD_LVL_FRAME_LIGHT_PLATE_HEIGHT_1,
                RegionNames.HUD_LIGHT_PLATE);

    }

    public void addExpBar(){
        HUDComponent tag = engine.createComponent(HUDComponent.class);
        ExperienceComponent expTag = engine.createComponent( ExperienceComponent.class );

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = GameHudConfig.HUD_EXP_BAR_DARK_PLATE_X;
        position.y = GameHudConfig.HUD_EXP_BAR_DARK_PLATE_Y;

        FreeMoveHudComponent freeMoveHud = engine.createComponent( FreeMoveHudComponent.class );
        freeMoveHud.freeMove = true;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameHudConfig.HUD_HEIGHT/8;
        dimension.height = GameHudConfig.HUD_EXP_BAR_DARK_PLATE_HEIGHT;

        RectangleBoundComponent rectangleBoundComponent = engine.createComponent(RectangleBoundComponent.class);
        rectangleBoundComponent.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = hudAtlas.findRegion( RegionNames.HUD_YELLOW_PLATE );

        Entity hpBar = new Entity();
        hpBar.add(position);
        hpBar.add(dimension);
        hpBar.add(texture);
        hpBar.add(tag);
        hpBar.add( freeMoveHud );
        hpBar.add( expTag );
        hpBar.add(rectangleBoundComponent);
        engine.addEntity(hpBar);
    }

    public void addStrongAttackButton(){
        addHudClickElement( 50, 90, 100, 100,1.0f,  RegionNames.HUD_GRAY_PLATE );
    }

    public void addAPFrame(){

        addHudNonFreeMoveNoClickElement( GameHudConfig.HUD_AP_FRAME_GRAY_PLATE_X, GameHudConfig.HUD_AP_FRAME_GRAY_PLATE_Y,
                GameHudConfig.HUD_AP_FRAME_GRAY_PLATE_WIDTH, GameHudConfig.HUD_AP_FRAME_GRAY_PLATE_HEIGHT,
                RegionNames.HUD_GRAY_PLATE);

        addHudNonFreeMoveNoClickElement( GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_X, GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_Y,
                GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_WIDTH, GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_HEIGHT,
                RegionNames.HUD_LIGHT_PLATE);

        addHudNonFreeMoveNoClickElement( GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_X_1, GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_Y_1,
                GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_WIDTH_1, GameHudConfig.HUD_AP_FRAME_LIGHT_PLATE_HEIGHT_1,
                RegionNames.HUD_LIGHT_PLATE);


    }


    private void addHudClickElement(float x, float y, float width, float height, float value ,String regionName){
        HUDComponent tag = engine.createComponent(HUDComponent.class);
        FreeMoveHudComponent freeMoveHud = engine.createComponent( FreeMoveHudComponent.class );
        freeMoveHud.freeMove = false;

        ClickableComponent click = engine.createComponent( ClickableComponent.class );

        ValueComponent valueComponent = engine.createComponent( ValueComponent.class );
        valueComponent.value = value;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;



        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = width;
        dimension.height = height;

        RectangleBoundComponent rectangleBoundComponent = engine.createComponent(RectangleBoundComponent.class);
        rectangleBoundComponent.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = hudAtlas.findRegion( regionName );

        Entity mainFrameFragment = new Entity();
        mainFrameFragment.add(position);
        mainFrameFragment.add(dimension);
        mainFrameFragment.add(texture);
        mainFrameFragment.add(tag);
        mainFrameFragment.add( click );
        mainFrameFragment.add( valueComponent );
        mainFrameFragment.add( freeMoveHud );
        mainFrameFragment.add(rectangleBoundComponent);
        engine.addEntity(mainFrameFragment);
    }


    private void addHudNoClickElement(float x, float y, float width, float height, String regionName){
        HUDComponent tag = engine.createComponent(HUDComponent.class);

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        FreeMoveHudComponent freeMoveHud = engine.createComponent( FreeMoveHudComponent.class );
        freeMoveHud.freeMove = true;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = width;
        dimension.height = height;

        RectangleBoundComponent rectangleBoundComponent = engine.createComponent(RectangleBoundComponent.class);
        rectangleBoundComponent.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = hudAtlas.findRegion( regionName );

        Entity mainFrameFragment = new Entity();
        mainFrameFragment.add(position);
        mainFrameFragment.add(dimension);
        mainFrameFragment.add(texture);
        mainFrameFragment.add(tag);
        mainFrameFragment.add( freeMoveHud );
        mainFrameFragment.add(rectangleBoundComponent);
        engine.addEntity(mainFrameFragment);
    }


    private void addHudNonFreeMoveNoClickElement(float x, float y, float width, float height, String regionName){
        HUDComponent tag = engine.createComponent(HUDComponent.class);
        FreeMoveHudComponent freeMoveHud = engine.createComponent( FreeMoveHudComponent.class );
        freeMoveHud.freeMove = false;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x;
        position.y = y;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = width;
        dimension.height = height;

        RectangleBoundComponent rectangleBoundComponent = engine.createComponent(RectangleBoundComponent.class);
        rectangleBoundComponent.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
        texture.region = hudAtlas.findRegion( regionName );

        Entity mainFrameFragment = new Entity();
        mainFrameFragment.add(position);
        mainFrameFragment.add(dimension);
        mainFrameFragment.add(texture);
        mainFrameFragment.add(tag);
        mainFrameFragment.add( freeMoveHud );
        mainFrameFragment.add(rectangleBoundComponent);
        engine.addEntity(mainFrameFragment);
    }





}

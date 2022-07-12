package pl.bozek.dungeon.system.hud;

import com.badlogic.ashley.core.Entity;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;

import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.bozek.dungeon.assets.AssetDescriptors;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;

import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.FreeMoveHudComponent;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.element.TextureComponent;
import pl.bozek.dungeon.component.tag.HUDComponent;

import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.config.GameHudConfig;

public class HudRenderSystem extends EntitySystem {

    private static final Family FAMILY = Family.one(
            HUDComponent.class
    ).get();

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    private final Viewport viewport;
    private final SpriteBatch batch;
    private BitmapFont font;


    private Array<Entity> renderQueue = new Array<>();
    private final GlyphLayout layout = new GlyphLayout();


    public HudRenderSystem(Viewport viewport, SpriteBatch batch, AssetManager assetManager) {
        this.viewport = viewport;
        this.batch = batch;
        this.font = assetManager.get(AssetDescriptors.BASIC_FONT);
        font.getData().setScale(GameHudConfig.FONT_RATIO);
    }



    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        draw();
        drawText();

        batch.end();
        renderQueue.clear();
    }


    private void drawText(){
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        for(Entity player : players) {
            PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( player );
            layout.setText(font, "" + playerStatistics.playerStatistics.getLvl());

            if(playerStatistics.playerStatistics.getLvl() >= 20){
                layout.width = GameHudConfig.HUD_LVL_FRAME_TEXT_X_20;
                layout.height = GameHudConfig.HUD_LVL_FRAME_TEXT_Y_20;
            } else if(playerStatistics.playerStatistics.getLvl() >= 10){
                layout.width = GameHudConfig.HUD_LVL_FRAME_TEXT_X_10;
                layout.height = GameHudConfig.HUD_LVL_FRAME_TEXT_Y_10;
            } else {
                layout.width = GameHudConfig.HUD_LVL_FRAME_TEXT_X;
                layout.height = GameHudConfig.HUD_LVL_FRAME_TEXT_Y ;
            }

            font.draw(batch, ""+playerStatistics.playerStatistics.getLvl(),
                    layout.width,
                    layout.height);


            /*
            font.draw(batch, ""+CurrentData.CURRENT_GAME_DEEP,
                    40, 40
                    );

*/

            if(!CurrentData.FREE_MOVEMENT){

                layout.setText( font, "" + playerStatistics.playerStatistics.getCurrentActionPoint());
                layout.width = GameHudConfig.HUD_AP_FRAME_TEXT_X_2;
                layout.height = GameHudConfig.HUD_AP_FRAME_TEXT_Y_2;
                font.draw(batch, "" + playerStatistics.playerStatistics.getCurrentActionPoint(),
                        layout.width,
                        layout.height);

                layout.setText( font, "AP:" );
                layout.width = GameHudConfig.HUD_AP_FRAME_TEXT_X;
                layout.height = GameHudConfig.HUD_AP_FRAME_TEXT_Y;
                font.draw(batch, "AP:",
                        layout.width,
                        layout.height);

            }


        }
    }

    private void draw() {
        for (Entity entity : renderQueue) {
            PositionComponent position = Mappers.POSITION.get(entity);
            DimensionComponent dimension = Mappers.DIMENSION.get(entity);
            TextureComponent texture = Mappers.TEXTURE.get(entity);
            FreeMoveHudComponent freeMoveHud = Mappers.FREE_MOVE_HUD.get( entity );


            if(!CurrentData.FREE_MOVEMENT){
                textureDrawStatic(position, dimension, texture);
            } else {
                if(freeMoveHud.freeMove){
                    textureDrawStatic(position, dimension, texture);
                }
            }







        }
    }




    private void textureDrawStatic(PositionComponent position, DimensionComponent dimension,
                                   TextureComponent texture){
        batch.draw(texture.region,
                position.x, position.y,
                dimension.width, dimension.height
        );
    }

}
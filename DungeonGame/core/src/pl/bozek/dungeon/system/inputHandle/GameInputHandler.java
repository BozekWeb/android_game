package pl.bozek.dungeon.system.inputHandle;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.RectangleBoundComponent;
import pl.bozek.dungeon.component.element.ValueComponent;
import pl.bozek.dungeon.component.tag.MapTileComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;




public class GameInputHandler extends InputBasic {

    private static final Family FLOOR_TILE_FAMILY = Family.all(
            MapTileComponent.class
    ).get();


    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    private Array<Entity> clickableTileArray = new Array<>();
    private Array<Entity> playerArray = new Array<>();


    public GameInputHandler(OrthographicCamera camera, PooledEngine engine) {
        super( camera, engine );
        ImmutableArray<Entity> tiles = engine.getEntitiesFor( FLOOR_TILE_FAMILY );
        clickableTileArray.addAll( tiles.toArray() );

        ImmutableArray<Entity> players = engine.getEntitiesFor( PLAYER_FAMILY );
        playerArray.addAll( players.toArray() );
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (CurrentData.PLAYER_CAN_CLICK) {
            for (Entity tile : clickableTileArray) {
                ValueComponent tileValue = Mappers.VALUE.get( tile );
                if (tileValue.value == 0) {
                    RectangleBoundComponent tileBound = Mappers.RECTANGLE_BOUND.get( tile );
                    MatrixPositionComponent tileMatrix = Mappers.MATRIX_POSITION.get( tile );

                    Vector3 clickPosition = getCamera().unproject( new Vector3( Gdx.input.getX( pointer ), Gdx.input.getY( pointer ), 0 ) );
                    if (tileBound.rectangle.contains( clickPosition.x, clickPosition.y )) {

                        boolean move = true;
                        for (Entity player : playerArray) {
                            MatrixPositionComponent playerMatrix = Mappers.MATRIX_POSITION.get( player );
                            if (playerMatrix.x == tileMatrix.x && playerMatrix.y == tileMatrix.y) {
                                move = false;
                            }
                        }

                        if (move) {
                            Gdx.app.debug( "GameInputHandler", "Matrix position (x, y) = (" + tileMatrix.x + ", " + tileMatrix.y + ")" );
                            CurrentData.PLAYER_FINAL_NODE_X = tileMatrix.x;
                            CurrentData.PLAYER_FINAL_NODE_Y = tileMatrix.y;
                            CurrentData.PLAYER_CAN_CLICK = false;
                        } else {
                                //CurrentData.PLAYER_DEAD = true;
                                Gdx.app.debug( "GameInputHandler", "Self Click: (" + tileMatrix.x + ", " + tileMatrix.y + ")" );
                        }
                    }
                }
            }
        }
        return true;
    }


}
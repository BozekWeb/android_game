package pl.bozek.dungeon.system.turnBase.basic;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.PathComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.util.PathUtils;

public class PlayerTurnStartSystem extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();



    public PlayerTurnStartSystem(){
        super(PLAYER_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(!CurrentData.PLAYER_CAN_CLICK && !CurrentData.PLAYER_TURN_START && CurrentData.PLAYER_TURN){
            CurrentData.CLICKED_ENEMY = null;

            PathComponent path = Mappers.PATH.get( entity );
            path.path = PathUtils.getPlayerPath(entity, (PooledEngine) getEngine() );
            if(path.path.size() > 0){
                Gdx.app.debug( "PlayerTurnStartSystem", "Delete player start position from path" );
                path.path.remove( 0 );
            }

            Gdx.app.debug( "PlayerTurnStartSystem", "Enemy clicked: " + CurrentData.CLICKED_ENEMY );

            if(CurrentData.CLICKED_ENEMY != null && path.path.size() == 1){
                CurrentData.PLAYER_TURN_START = true;
                if(CurrentData.PLAYER_ATTACK_TYPE == 1){
                    Gdx.app.debug( "PlayerTurnStartSystem", "Player declare normal attack" );
                    CurrentData.PLAYER_CAN_ATTACK = true;
                } else if(CurrentData.PLAYER_ATTACK_TYPE == 2) {
                    Gdx.app.debug( "PlayerTurnStartSystem", "Player declare strong attack" );
                    CurrentData.PLAYER_STRONG_ATTACK = true;
                }

            } else if(path.path.size() > 0){
                Gdx.app.debug( "PlayerTurnStartSystem", "Player move to declared point" );
                CurrentData.PLAYER_TURN_START = true;
                CurrentData.PLAYER_CAN_MOVE = true;
            } else{
                Gdx.app.debug( "PlayerTurnStartSystem", "Player cannot move: Enemy or other object blocks" );
                CurrentData.PLAYER_CAN_CLICK = true;
            }




        }
    }
}

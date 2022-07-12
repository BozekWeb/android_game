package pl.bozek.dungeon.system.turnBase.freeMovement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class BreakFreeMovementSystem extends IteratingSystem {

    private static Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();



    public BreakFreeMovementSystem( ){
        super(PLAYER_FAMILY);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(CurrentData.BREAK_FREE_MOVEMENT){
            Gdx.app.debug( "BreakFreeMovementSystem", "FreeMove break" );



            PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( entity );
            playerStatistics.playerStatistics.setCurrentActionPoint( playerStatistics.playerStatistics.getMaxActionPoint() );

            CurrentData.FREE_MOVEMENT = false;
            CurrentData.BREAK_FREE_MOVEMENT = false;

            CurrentData.PLAYER_CAN_MOVE = false;
            CurrentData.PLAYER_TURN_START = false;
            CurrentData.PLAYER_CAN_CLICK = true;




        }
    }


}

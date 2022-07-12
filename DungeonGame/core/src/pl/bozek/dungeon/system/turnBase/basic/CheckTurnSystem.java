package pl.bozek.dungeon.system.turnBase.basic;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class CheckTurnSystem extends EntitySystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public CheckTurnSystem(){}

    @Override
    public void update(float deltaTime) {

        if(CurrentData.FREE_MOVEMENT){
            CurrentData.PLAYER_TURN = true;
            CurrentData.ENEMY_TURN = false;
        } else {
            ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
            for(Entity player : players) {
                PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( player );

                if(playerStatistics.playerStatistics.getCurrentActionPoint() > 0){

                    CurrentData.PLAYER_TURN = true;
                    CurrentData.ENEMY_TURN = false;
                } else {

                    CurrentData.PLAYER_TURN = false;
                    CurrentData.PLAYER_CAN_CLICK = false;
                    CurrentData.ENEMY_TURN = true;
                }
            }
        }
    }
}

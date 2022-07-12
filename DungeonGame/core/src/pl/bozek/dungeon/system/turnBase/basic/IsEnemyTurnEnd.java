package pl.bozek.dungeon.system.turnBase.basic;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class IsEnemyTurnEnd {

    private static Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public static void isAllEnemyMove(PooledEngine engine){

        ImmutableArray<Entity> players = engine.getEntitiesFor( PLAYER_FAMILY );

        int size = CurrentData.ENEMY_ACTION_ARRAY.size;



        if(size == 0){


            for (Entity player : players) {
                PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( player );
                playerStatistics.playerStatistics.setCurrentActionPoint(playerStatistics.playerStatistics.getMaxActionPoint());

            }


            CurrentData.PLAYER_TURN = true;
            CurrentData.PLAYER_TURN_START = false;
            CurrentData.ENEMY_TURN = false;
            CurrentData.ENEMY_TURN_START = false;

        }


    }

}

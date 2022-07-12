package pl.bozek.dungeon.system.turnBase.basic;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;
import pl.bozek.dungeon.component.element.FindEnemyComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;

public class EnemyTurnStartSystem extends EntitySystem {

    private static final Family ENEMY_FAMILY = Family.all(
            EnemyComponent.class
    ).get();


    public EnemyTurnStartSystem(){}

    @Override
    public void update(float deltaTime) {
        if(CurrentData.ENEMY_TURN && !CurrentData.ENEMY_TURN_START){
            ImmutableArray<Entity> enemies = getEngine().getEntitiesFor(ENEMY_FAMILY);

            for (Entity enemy : enemies) {
                FindEnemyComponent isEnemyFound = Mappers.FIND_ENEMY.get( enemy );

                if(isEnemyFound.isEnemyFound){
                    EnemyStatisticsComponent enemyStatistics = Mappers.ENEMY_STATISTICS.get( enemy );
                    enemyStatistics.enemyStatistics.setCurrentActionPoint( enemyStatistics.enemyStatistics.getMaxActionPoint() );
                    CurrentData.ENEMY_ACTION_ARRAY.add( enemy );
                }
            }

            CurrentData.ENEMY_TURN_START = true;
            CurrentData.ENEMY_FIND_PATH = false;





        }
    }
}

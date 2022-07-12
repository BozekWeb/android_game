package pl.bozek.dungeon.system.enemy;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class EnemyDeadSystem extends EntitySystem {

    private static Family ENEMY_FAMILY = Family.all(
            EnemyComponent.class
    ).get();

    private static Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public EnemyDeadSystem() {
    }


    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> players = getEngine().getEntitiesFor( PLAYER_FAMILY );
        ImmutableArray<Entity> enemies = getEngine().getEntitiesFor( ENEMY_FAMILY );



        for (Entity player : players) {
            for (Entity enemy : enemies) {

                EnemyStatisticsComponent enemyStatistics = Mappers.ENEMY_STATISTICS.get( enemy );
                PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( player );

                if (enemyStatistics.enemyStatistics.getCurrentHitPoint() <= 0) {

                    int lvl = playerStatistics.playerStatistics.getLvl();
                    int deep = playerStatistics.playerStatistics.getDeep();
                    int cExp = playerStatistics.playerStatistics.getCurrentExpPoint();
                    int addExp;

                    if(deep < lvl ){
                        if(lvl - deep > 3){
                            addExp = 0;
                        } else{
                            addExp = 1 + deep * 2 - (lvl - deep);
                        }
                    } else {
                        addExp = 1 + deep;
                    }


                    playerStatistics.playerStatistics.setCurrentExpPoint( cExp + addExp );

                    getEngine().removeEntity( enemy );

                }
            }
        }
    }
}

package pl.bozek.dungeon.system.enemy;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;


import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;

import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

class EnemyAttackSystem{

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    static void enemySingleAttack(PooledEngine engine){
        Entity enemy = CurrentData.ENEMY_ACTION_ARRAY.get( 0 );
        EnemyStatisticsComponent enemyStat = Mappers.ENEMY_STATISTICS.get( enemy );

        int minDmg = enemyStat.enemyStatistics.getMinDamage();
        int maxDmg = enemyStat.enemyStatistics.getMaxDamage();
        int rand = MathUtils.random( maxDmg-minDmg );
        int finalDmg = minDmg + rand;

        Gdx.app.debug( "EnemyAttackSystem", "Enemy final DMG: " + finalDmg );

        ImmutableArray<Entity> players = engine.getEntitiesFor(PLAYER_FAMILY);
        for (Entity player : players) {

            PlayerStatisticsComponent playerStat = Mappers.PLAYER_STATISTICS.get( player );
            int armor = playerStat.playerStatistics.getArmor();

            if(armor >= finalDmg ){
                playerStat.playerStatistics.setCurrentHitPoint( playerStat.playerStatistics.getCurrentHitPoint() - 1 );
            } else {

                playerStat.playerStatistics.setCurrentHitPoint( playerStat.playerStatistics.getCurrentHitPoint() - (finalDmg - armor) );

            }
        }
    }



}






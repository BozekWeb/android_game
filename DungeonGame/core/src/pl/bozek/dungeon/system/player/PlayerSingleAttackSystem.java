package pl.bozek.dungeon.system.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class PlayerSingleAttackSystem extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public PlayerSingleAttackSystem(){
        super(PLAYER_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(CurrentData.PLAYER_CAN_ATTACK && CurrentData.CLICKED_ENEMY != null){

            PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( entity );
            EnemyStatisticsComponent enemyStatistics = Mappers.ENEMY_STATISTICS.get( CurrentData.CLICKED_ENEMY );


            int playerDMG = MathUtils.random( playerStatistics.playerStatistics.getMinDamage(), playerStatistics.playerStatistics.getMaxDamage() );
            Gdx.app.debug( "PlayerSingleAttack", "playerDMG = "+ playerDMG );

            float dodge = enemyStatistics.enemyStatistics.getDodgeChance();

            float randDodge = MathUtils.random( 100 - 1 );

            playerStatistics.playerStatistics.setCurrentActionPoint(playerStatistics.playerStatistics.getCurrentActionPoint()-1);

            if(randDodge > dodge){

                int enemyHP = enemyStatistics.enemyStatistics.getCurrentHitPoint();
                int armor = enemyStatistics.enemyStatistics.getArmor();

                if(armor >= playerDMG ){
                    enemyStatistics.enemyStatistics.setCurrentHitPoint( enemyHP - 1 );
                } else {
                    Gdx.app.debug( "PlayerSingleAttack", "DMG to Enemy = "+ (playerDMG - armor) );
                    enemyStatistics.enemyStatistics.setCurrentHitPoint( enemyHP - (playerDMG - armor) );
                }

            } else {
                Gdx.app.debug( "PlayerSingleAttack", "Enemy dodge player attack: Try use strong one" );
                int enemyHP = enemyStatistics.enemyStatistics.getCurrentHitPoint();
                enemyStatistics.enemyStatistics.setCurrentHitPoint( enemyHP );
            }


            CurrentData.PLAYER_CAN_ATTACK = false;
            CurrentData.PLAYER_TURN_START = false;
            CurrentData.PLAYER_CAN_CLICK = true;

        }
    }
}

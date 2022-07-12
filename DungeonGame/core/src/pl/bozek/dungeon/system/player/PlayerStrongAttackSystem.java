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

public class PlayerStrongAttackSystem extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public PlayerStrongAttackSystem(){
        super(PLAYER_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(CurrentData.PLAYER_STRONG_ATTACK && CurrentData.CLICKED_ENEMY != null){
            PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( entity );
            if(playerStatistics.playerStatistics.getCurrentActionPoint() >= 3){
                EnemyStatisticsComponent enemyStatistics = Mappers.ENEMY_STATISTICS.get( CurrentData.CLICKED_ENEMY );

                int playerDMG = 1 + MathUtils.random( playerStatistics.playerStatistics.getMinDamage(), playerStatistics.playerStatistics.getMaxDamage() );
                Gdx.app.debug( "PlayerStrongAttack", "playerDMG = "+ playerDMG );

                playerStatistics.playerStatistics.setCurrentActionPoint(playerStatistics.playerStatistics.getCurrentActionPoint()-3);
                int enemyHP = enemyStatistics.enemyStatistics.getCurrentHitPoint();
                int armor = enemyStatistics.enemyStatistics.getArmor();
                armor /= 2;
                Gdx.app.debug( "PlayerStrongAttack", "Half enemy armor: "+ armor );

                if(armor >= playerDMG ){
                    enemyStatistics.enemyStatistics.setCurrentHitPoint( enemyHP - 1 );
                } else {
                    Gdx.app.debug( "PlayerStrongAttack", "DMG to Enemy = "+ (playerDMG - armor) );
                    enemyStatistics.enemyStatistics.setCurrentHitPoint( enemyHP - (playerDMG - armor) );
                }
            } else{
                Gdx.app.debug( "PlayerStrongAttack", "Cannot use strong attack, with less than 3 AP");
            }






            CurrentData.PLAYER_STRONG_ATTACK = false;
            CurrentData.PLAYER_TURN_START = false;
            CurrentData.PLAYER_CAN_CLICK = true;

        }
    }
}

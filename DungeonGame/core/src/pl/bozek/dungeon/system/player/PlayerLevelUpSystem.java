package pl.bozek.dungeon.system.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class PlayerLevelUpSystem extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public PlayerLevelUpSystem(){
        super(PLAYER_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( entity );
        int cExp = playerStatistics.playerStatistics.getCurrentExpPoint();
        int nLvl = playerStatistics.playerStatistics.getLvl() + 1;
        int nExp = (3 * nLvl);
        int rest;

        if(nLvl == 31){
            playerStatistics.playerStatistics.setCurrentExpPoint( nExp );
        } else if(cExp >= nExp){
            playerStatistics.playerStatistics.setLvl( nLvl );
            rest = cExp - nExp;
            playerStatistics.playerStatistics.setCurrentExpPoint( rest );
            playerStatistics.playerStatistics.setCurrentHitPoint( playerStatistics.playerStatistics.getMaxHitPoint() );
        }



    }
}

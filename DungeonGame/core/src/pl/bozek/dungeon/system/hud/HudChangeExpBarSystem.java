package pl.bozek.dungeon.system.hud;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.ExperienceComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.config.GameHudConfig;

public class HudChangeExpBarSystem extends IteratingSystem {

    private static final Family EXP_FAMILY = Family.all(
            ExperienceComponent.class
    ).get();

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public HudChangeExpBarSystem(){
        super(EXP_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        for(Entity player : players) {
            PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( player );
            DimensionComponent expBarDimension = Mappers.DIMENSION.get( entity );

            float cExp = playerStatistics.playerStatistics.getCurrentExpPoint();
            int cLvl = playerStatistics.playerStatistics.getLvl() + 1;
            float ratio = cExp / (3 * cLvl);

            expBarDimension.width = GameHudConfig.HUD_EXP_BAR_DARK_PLATE_WIDTH * ratio;

        }
    }
}

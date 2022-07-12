package pl.bozek.dungeon.system.hud;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.assets.RegionNames;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.element.TextureComponent;
import pl.bozek.dungeon.component.tag.HPComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;


public class HudChangeHitPointSystem extends IteratingSystem {

    private static final Family HP_FAMILY = Family.all(
            HPComponent.class
    ).get();

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    private TextureAtlas hudAtlas;



    public HudChangeHitPointSystem(AssetManager assetManager ){
        super(HP_FAMILY);
        hudAtlas = assetManager.get( AssetDescriptors.HUD_TILE);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        for(int i = 0; i < players.size(); i++) {
            PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( players.get( i ) );
            TextureComponent texture = Mappers.TEXTURE.get( entity );

            if (playerStatistics.playerStatistics.getCurrentHitPoint() >= 10) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_10 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 9) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_09 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 8) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_08 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 7) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_07 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 6) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_06 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 5) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_05 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 4) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_04 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 3) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_03 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 2) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_02 );
            } else if (playerStatistics.playerStatistics.getCurrentHitPoint() == 1) {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_01 );
            } else {
                texture.region = hudAtlas.findRegion( RegionNames.HUD_HP_BAR_00 );

                CurrentData.PLAYER_DEAD = true;

            }
        }
    }
}

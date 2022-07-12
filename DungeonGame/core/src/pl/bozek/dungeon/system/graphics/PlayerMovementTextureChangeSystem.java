package pl.bozek.dungeon.system.graphics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.assets.RegionNames;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.AnimatedTextureComponent;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class PlayerMovementTextureChangeSystem extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    private TextureAtlas playerAtlas;

    public PlayerMovementTextureChangeSystem(AssetManager assetManager){
        super(PLAYER_FAMILY);
        this.playerAtlas = assetManager.get( AssetDescriptors.ENTITY_TILESET );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent playerMovement = Mappers.MOVEMENT.get( entity );

        if(playerMovement.xSpeed != 0 || playerMovement.ySpeed != 0){
            AnimatedTextureComponent playerTexture = Mappers.A_TEXTURE.get( entity);

            if(playerMovement.xSpeed < 0){
                GraphicsUtility.setAnimation( RegionNames.knight_m_idle_left, playerTexture, playerAtlas);
            } else if (playerMovement.xSpeed > 0) {
                GraphicsUtility.setAnimation( RegionNames.knight_m_idle_right, playerTexture, playerAtlas);
            } else if(playerMovement.ySpeed > 0){
                GraphicsUtility.setAnimation( RegionNames.knight_m_idle_up, playerTexture, playerAtlas);
            } else if(playerMovement.ySpeed < 0){
                GraphicsUtility.setAnimation( RegionNames.knight_m_idle_down, playerTexture, playerAtlas);
            }


        }
    }
}

package pl.bozek.dungeon.system.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import pl.bozek.dungeon.component.element.AnimatedTextureComponent;

public class GraphicsUtility {

    public static void setAnimation(String region, AnimatedTextureComponent playerTexture, TextureAtlas playerAtlas){
        playerTexture.animation = new Animation<>( 0.09f,
                playerAtlas.findRegions(region),
                Animation.PlayMode.LOOP
        );
    }

    private GraphicsUtility(){}
}

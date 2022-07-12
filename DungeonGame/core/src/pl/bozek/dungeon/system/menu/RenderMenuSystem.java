package pl.bozek.dungeon.system.menu;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.AnimatedTextureComponent;
import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;
import pl.bozek.dungeon.component.element.FindEnemyComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.element.TextureComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class RenderMenuSystem extends EntitySystem {

    private final Viewport viewport;
    private final SpriteBatch batch;
    private float time;
    Skin skin = new Skin( Gdx.files.internal( "hud/uiskin.json" ) );



    Stage stage;
    private Array<Entity> renderQueue;

    public RenderMenuSystem(Viewport viewport, SpriteBatch batch, Stage stage) {
        this.viewport = viewport;
        this.batch = batch;
        this.stage = stage;
        renderQueue = new Array<>();
    }



    @Override
    public void update(float deltaTime) {


        time += deltaTime;
        viewport.apply();
        draw();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();







        batch.end();
        renderQueue.clear();

    }

    private void draw() {


    }



    private void textureDraw(PositionComponent position, DimensionComponent dimension,
                             AnimatedTextureComponent aTexture){



        TextureRegion textureRegion = (TextureRegion) aTexture.animation.getKeyFrame(time);
        batch.draw(textureRegion,
                position.x, position.y,
                dimension.width, dimension.height);
    }



    private void textureDrawStatic(PositionComponent position, DimensionComponent dimension,
                                   TextureComponent texture){
        batch.draw(texture.region,
                position.x, position.y,
                dimension.width, dimension.height
        );
    }


}

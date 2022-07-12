package pl.bozek.dungeon.system.graphics;

import com.badlogic.ashley.core.Entity;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import pl.bozek.dungeon.component.tag.HUDComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class RenderSystem extends EntitySystem {

    private static final Family FAMILY = Family.one(
            TextureComponent.class,
            AnimatedTextureComponent.class
    ).exclude(HUDComponent.class).get();



    private final Viewport viewport;
    private final SpriteBatch batch;
    private float time;


    private Array<Entity> renderQueue;

    public RenderSystem(Viewport viewport, SpriteBatch batch) {
        this.viewport = viewport;
        this.batch = batch;

        renderQueue = new Array<>();
    }



    @Override
    public void update(float deltaTime) {
        ImmutableArray<Entity> entities = getEngine().getEntitiesFor(FAMILY);
        renderQueue.addAll(entities.toArray());

        time += deltaTime;
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        draw();

        batch.end();
        renderQueue.clear();
    }

    private void draw() {
        for (Entity entity : renderQueue) {
            PositionComponent position = Mappers.POSITION.get(entity);
            DimensionComponent dimension = Mappers.DIMENSION.get(entity);
            TextureComponent texture = Mappers.TEXTURE.get(entity);
            AnimatedTextureComponent animatedTexture = Mappers.A_TEXTURE.get(entity);
            PlayerComponent player = Mappers.PLAYER_TAG.get(entity);
            EnemyComponent enemy = Mappers.ENEMY_TAG.get( entity );

            if(animatedTexture == null) {
                textureDrawStatic(position, dimension, texture);
            } else if(player != null){
                textureDraw(position, dimension, animatedTexture);
            } else{
                textureDraw(position, dimension, animatedTexture);
            }

            if(enemy != null && !CurrentData.FREE_MOVEMENT){
                FindEnemyComponent isEnemyFound = Mappers.FIND_ENEMY.get( entity );

                if(isEnemyFound.isEnemyFound) {

                    EnemyStatisticsComponent enemyStatistics = Mappers.ENEMY_STATISTICS.get( entity );

                    float cHP = enemyStatistics.enemyStatistics.getCurrentHitPoint();
                    int mHP = enemyStatistics.enemyStatistics.getMaxHitPoint();
                    float ratio = cHP / mHP;

                    batch.draw( texture.region,
                            position.x - dimension.width / 8, position.y,
                            dimension.width / 8, dimension.height * ratio
                    );
                }
            }
        }
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
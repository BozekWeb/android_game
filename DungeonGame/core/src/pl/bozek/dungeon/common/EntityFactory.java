package pl.bozek.dungeon.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.assets.RegionNames;
import pl.bozek.dungeon.component.element.AnimatedTextureComponent;
import pl.bozek.dungeon.component.element.ColorComponent;
import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;
import pl.bozek.dungeon.component.element.FindEnemyComponent;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.element.PathComponent;
import pl.bozek.dungeon.component.element.PlayerCanMoveComponent;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.element.RectangleBoundComponent;
import pl.bozek.dungeon.component.element.TextureComponent;
import pl.bozek.dungeon.component.element.ValueComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.system.enemy.EnemyStatistics;
import pl.bozek.dungeon.customType.PlayerStatistics;

public class EntityFactory {

    private final PooledEngine engine;
    private final TextureAtlas playerAtlas;
    private final TextureAtlas hudAtlas;

    public EntityFactory(PooledEngine engine, AssetManager assetManager){
        this.engine = engine;
        playerAtlas = assetManager.get(AssetDescriptors.ENTITY_TILESET);
        hudAtlas = assetManager.get(AssetDescriptors.HUD_TILE);
    }

    public void addPlayer(int x, int y, PlayerStatistics pStatistics){
        PlayerComponent playerTag = engine.createComponent(PlayerComponent.class);


        PathComponent path = engine.createComponent( PathComponent.class );
        path.path = null;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = (GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP );
        dimension.height = GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;

        MatrixPositionComponent matrix = engine.createComponent(MatrixPositionComponent.class);
        matrix.x = x;
        matrix.y = y;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x * GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;
        position.y = y * GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;

        PlayerStatisticsComponent playerStatistics = engine.createComponent( PlayerStatisticsComponent.class );
        playerStatistics.playerStatistics = pStatistics;

        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        movementComponent.xSpeed = 0;
        movementComponent.ySpeed = 0;

        RectangleBoundComponent rectangle = engine.createComponent(RectangleBoundComponent.class);
        rectangle.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        ColorComponent color = engine.createComponent(ColorComponent.class);
        color.color = new Color(Color.BROWN);

        PlayerCanMoveComponent canMove = engine.createComponent( PlayerCanMoveComponent.class );
        canMove.canMove = false;

        AnimatedTextureComponent animation = engine.createComponent(AnimatedTextureComponent.class);
        animation.animation = new Animation<>( 0.09f,
                playerAtlas.findRegions( RegionNames.knight_m_idle_down ),
                Animation.PlayMode.LOOP );

        Entity player = new Entity();
        player.add(playerTag);
        player.add(dimension);
        player.add(position);
        player.add(rectangle);
        player.add(color);
        player.add( path );
        player.add(animation);
        player.add(playerStatistics);
        player.add(matrix);
        player.add(movementComponent);
        engine.addEntity(player);
    }


    public void addStairs(int x, int y){

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = (GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP );
        dimension.height = GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;

        MatrixPositionComponent matrix = engine.createComponent(MatrixPositionComponent.class);
        matrix.x = x;
        matrix.y = y;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x * GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;
        position.y = y * GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;

        RectangleBoundComponent rectangle = engine.createComponent(RectangleBoundComponent.class);
        rectangle.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        ColorComponent color = engine.createComponent(ColorComponent.class);
        color.color = new Color(Color.PINK);

        AnimatedTextureComponent animation = engine.createComponent(AnimatedTextureComponent.class);
        animation.animation = new Animation<>( 0.09f,
                playerAtlas.findRegions( RegionNames.knight_m_idle_down ),
                Animation.PlayMode.LOOP );

        Entity player = new Entity();

        player.add(dimension);
        player.add(position);
        player.add(rectangle);
        player.add(color);
        player.add(animation);
        player.add(matrix);
        engine.addEntity(player);
    }

    public void addEnemy(int x, int y, Color color1){
        EnemyComponent enemyTag = engine.createComponent(EnemyComponent.class);

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;
        dimension.height = GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;

        MatrixPositionComponent matrix = engine.createComponent(MatrixPositionComponent.class);
        matrix.x = x;
        matrix.y = y;

        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = (x * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP ;
        position.y = y * GameConfig.MAP_DIMENSION * GameConfig.RATIO_FOR_MAP;

        MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
        movementComponent.xSpeed = 0;
        movementComponent.ySpeed = 0;

        RectangleBoundComponent rectangle = engine.createComponent(RectangleBoundComponent.class);
        rectangle.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        TextureComponent texture = engine.createComponent( TextureComponent.class );
        texture.region = hudAtlas.findRegion( RegionNames.HUD_YELLOW_PLATE );

        ColorComponent color = engine.createComponent(ColorComponent.class);
        color.color = color1;
        AnimatedTextureComponent animation = engine.createComponent(AnimatedTextureComponent.class);

        EnemyStatisticsComponent enemyStatistics = engine.createComponent( EnemyStatisticsComponent.class);
        ValueComponent enemyType = engine.createComponent(ValueComponent.class);
        enemyType.value = MathUtils.random(9);
        if(enemyType.value == 0 || enemyType.value == 1 || enemyType.value == 2 || enemyType.value == 3 || enemyType.value == 4){
            enemyStatistics.enemyStatistics = new EnemyStatistics( 0, CurrentData.CURRENT_GAME_DEEP );
            animation.animation = new Animation<>( 0.09f,
                    playerAtlas.findRegions( RegionNames.big_demon_idle_anim ),
                    Animation.PlayMode.LOOP );

        } else if(enemyType.value == 5 || enemyType.value == 6){
            enemyStatistics.enemyStatistics = new EnemyStatistics( 1, CurrentData.CURRENT_GAME_DEEP );
            animation.animation = new Animation<>(0.09f,
                    playerAtlas.findRegions(RegionNames.big_zombie_idle_anim),
                    Animation.PlayMode.LOOP);

        } else if(enemyType.value == 7 || enemyType.value == 8 || enemyType.value == 9){
            enemyStatistics.enemyStatistics = new EnemyStatistics( 2, CurrentData.CURRENT_GAME_DEEP );
            animation.animation = new Animation<>(0.09f,
                    playerAtlas.findRegions(RegionNames.chort_idle_anim),
                    Animation.PlayMode.LOOP);

        }

        FindEnemyComponent isEnemyFound = engine.createComponent( FindEnemyComponent.class );
        isEnemyFound.isEnemyFound = false;

        PathComponent path = engine.createComponent( PathComponent.class );

        Entity enemy = new Entity();
            enemy.add(dimension);
            enemy.add(position);
            enemy.add(rectangle);
            enemy.add(movementComponent);
            enemy.add(enemyType);
            enemy.add(enemyTag);
            enemy.add(color);
            enemy.add( texture );
            enemy.add(animation);
            enemy.add(matrix);
            enemy.add( path );
            enemy.add( isEnemyFound );
            enemy.add( enemyStatistics );
        engine.addEntity(enemy);
    }

}

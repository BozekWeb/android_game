package pl.bozek.dungeon.component;

import com.badlogic.ashley.core.ComponentMapper;

import pl.bozek.dungeon.component.element.AnimatedTextureComponent;
import pl.bozek.dungeon.component.element.ColorComponent;
import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;
import pl.bozek.dungeon.component.element.FindEnemyComponent;
import pl.bozek.dungeon.component.element.FreeMoveHudComponent;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.element.PathComponent;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.element.RectangleBoundComponent;
import pl.bozek.dungeon.component.element.TextureComponent;
import pl.bozek.dungeon.component.element.ValueComponent;
import pl.bozek.dungeon.component.tag.ClickableComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;


/**
 *  Class store info about all components used in project.
 * */

public class Mappers {

    /**
     *  Element mapper - used to store data about element component
     * */
    public static final ComponentMapper<PathComponent> PATH =
            ComponentMapper.getFor( PathComponent.class );

    public static final ComponentMapper<FindEnemyComponent> FIND_ENEMY =
            ComponentMapper.getFor(FindEnemyComponent.class);

    public static final ComponentMapper<PositionComponent> POSITION =
            ComponentMapper.getFor(PositionComponent.class);

    public static final ComponentMapper<DimensionComponent> DIMENSION =
            ComponentMapper.getFor(DimensionComponent.class);

    public static final ComponentMapper<RectangleBoundComponent> RECTANGLE_BOUND =
            ComponentMapper.getFor(RectangleBoundComponent.class);

    public static final ComponentMapper<ColorComponent> COLOR =
            ComponentMapper.getFor(ColorComponent.class);

    public static final ComponentMapper<ValueComponent> VALUE =
            ComponentMapper.getFor(ValueComponent.class);

    public static final ComponentMapper<TextureComponent> TEXTURE =
            ComponentMapper.getFor(TextureComponent.class);

    public static final ComponentMapper<MatrixPositionComponent> MATRIX_POSITION =
            ComponentMapper.getFor(MatrixPositionComponent.class);

    public static final ComponentMapper<AnimatedTextureComponent> A_TEXTURE =
            ComponentMapper.getFor(AnimatedTextureComponent.class);

    public static final ComponentMapper<MovementComponent> MOVEMENT =
            ComponentMapper.getFor(MovementComponent.class);

    public static final ComponentMapper<FreeMoveHudComponent> FREE_MOVE_HUD =
            ComponentMapper.getFor(FreeMoveHudComponent.class);

    public static final ComponentMapper<PlayerStatisticsComponent> PLAYER_STATISTICS =
            ComponentMapper.getFor(PlayerStatisticsComponent.class);

    public static final ComponentMapper<EnemyStatisticsComponent> ENEMY_STATISTICS =
            ComponentMapper.getFor(EnemyStatisticsComponent.class);



    /**
     *  Tag mapper - used to store data about tag component
     * */

    public static final ComponentMapper<PlayerComponent> PLAYER_TAG =
            ComponentMapper.getFor(PlayerComponent.class);

    public static final ComponentMapper<EnemyComponent> ENEMY_TAG =
            ComponentMapper.getFor(EnemyComponent.class);

    public static final ComponentMapper<ClickableComponent> CLICKABLE_TAG =
            ComponentMapper.getFor(ClickableComponent.class);



    private Mappers(){}
}

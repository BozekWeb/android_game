package pl.bozek.dungeon.common;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.component.element.ColorComponent;
import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.element.RectangleBoundComponent;
import pl.bozek.dungeon.component.element.TextureComponent;
import pl.bozek.dungeon.component.element.ValueComponent;
import pl.bozek.dungeon.component.tag.MapTileComponent;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.util.GdxUtils;

public class MapFactory {
    private final PooledEngine engine;
    private final TextureAtlas mapAtlas;

    public MapFactory(PooledEngine engine, AssetManager assetManager){
        this.engine = engine;
        this.mapAtlas = assetManager.get(AssetDescriptors.MAP_TILESET);
    }


    public void addTile(final int x, final int y, float i, String regionName){
        MapTileComponent mapTileTag = engine.createComponent(MapTileComponent.class);

        MatrixPositionComponent matrix = engine.createComponent(MatrixPositionComponent.class);
        matrix.x = x;
        matrix.y = y;

        DimensionComponent dimension = engine.createComponent(DimensionComponent.class);
        dimension.width = GameConfig.MAP_DIMENSION;
        dimension.height = GameConfig.MAP_DIMENSION;


        PositionComponent position = engine.createComponent(PositionComponent.class);
        position.x = x * GameConfig.MAP_DIMENSION_FINAL;
        position.y = y * GameConfig.MAP_DIMENSION_FINAL;

        RectangleBoundComponent rectangleBound = engine.createComponent(RectangleBoundComponent.class);
        rectangleBound.rectangle = new Rectangle(position.x, position.y, dimension.width, dimension.height);

        ValueComponent value = engine.createComponent(ValueComponent.class);
        value.value = i;

        ColorComponent color = engine.createComponent(ColorComponent.class);
        color.color = GdxUtils.getColor(i);

        TextureComponent texture = engine.createComponent(TextureComponent.class);
            texture.region = mapAtlas.findRegion(regionName);

        Entity mapTile = new Entity();
        mapTile.add(mapTileTag);
        mapTile.add(matrix);
        mapTile.add(dimension);
        mapTile.add(position);
        mapTile.add(rectangleBound);
        mapTile.add(value);
        mapTile.add(color);
        mapTile.add(texture);
        engine.addEntity(mapTile);

    }




}

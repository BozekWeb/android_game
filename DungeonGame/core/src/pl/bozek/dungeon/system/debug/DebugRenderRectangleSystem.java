package pl.bozek.dungeon.system.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.ColorComponent;
import pl.bozek.dungeon.component.element.DimensionComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.element.RectangleBoundComponent;
import pl.bozek.dungeon.component.tag.HUDComponent;

public class DebugRenderRectangleSystem extends IteratingSystem {

    private static final Family FAMILY = Family.exclude(
            HUDComponent.class
    ).get();


    private final Viewport viewport;
    private final ShapeRenderer renderer;

    public DebugRenderRectangleSystem(Viewport viewport, ShapeRenderer renderer){
        super(FAMILY);
        this.viewport = viewport;
        this.renderer = renderer;
    }

    @Override
    public void update(float deltaTime) {
        Color oldColor = renderer.getColor();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        super.update(deltaTime);

        renderer.end();
        renderer.setColor(oldColor);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.POSITION.get(entity);
        DimensionComponent dimension = Mappers.DIMENSION.get(entity);
        ColorComponent color = Mappers.COLOR.get(entity);

        RectangleBoundComponent rectangle = Mappers.RECTANGLE_BOUND.get(entity);
        rectangle.rectangle.set(position.x, position.y, dimension.width, dimension.height);

        renderer.setColor(color.color);
        renderer.rect(rectangle.rectangle.x, rectangle.rectangle.y, rectangle.rectangle.width, rectangle.rectangle.height);

    }
}

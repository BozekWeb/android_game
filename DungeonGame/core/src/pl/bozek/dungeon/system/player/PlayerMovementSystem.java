package pl.bozek.dungeon.system.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;

public class PlayerMovementSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    private OrthographicCamera camera;

    public PlayerMovementSystem(OrthographicCamera camera){
        super(FAMILY);
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        float x = camera.position.x;
        float y = camera.position.y;

        PositionComponent position = Mappers.POSITION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        position.x += movement.xSpeed;
        position.y += movement.ySpeed;

        camera.position.set(x + movement.xSpeed, y + movement.ySpeed, 0);
        camera.update();
   }
}

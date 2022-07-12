package pl.bozek.dungeon.system.enemy;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;


public class EnemyMovementSystem extends IteratingSystem {
    private static final Family FAMILY = Family.all(
            EnemyComponent.class
    ).get();



    public EnemyMovementSystem( ){
        super(FAMILY);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {


        PositionComponent position = Mappers.POSITION.get(entity);
        MovementComponent movement = Mappers.MOVEMENT.get(entity);

        position.x += movement.xSpeed;
        position.y += movement.ySpeed;


   }
}

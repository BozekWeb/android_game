package pl.bozek.dungeon.system.turnBase.freeMovement;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.FindEnemyComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;

public class StartFreeMovementSystem extends EntitySystem {

    private static Family ENEMY_FAMILY = Family.all(
            EnemyComponent.class
    ).get();


    public StartFreeMovementSystem(){}


    @Override
    public void update(float deltaTime) {
        if(!CurrentData.FREE_MOVEMENT){
            ImmutableArray<Entity> enemies = getEngine().getEntitiesFor( ENEMY_FAMILY );

            int check = 0;

            for (Entity enemy : enemies){
                FindEnemyComponent find = Mappers.FIND_ENEMY.get( enemy );
                if(find.isEnemyFound){
                    check++;
                }
            }

            if(check == 0){
                CurrentData.FREE_MOVEMENT = true;
            }



        }



    }


}
